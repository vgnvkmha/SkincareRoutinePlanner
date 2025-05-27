package com.example.skincareroutineplanner.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.skincareroutineplanner.domain.schedule.Routine
import com.example.skincareroutineplanner.domain.schedule.distributeOverWeek
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageRepository
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageStats
import com.example.skincareroutineplanner.presentation.screens.settings.composables.SelectedOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProductViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository: ProductsRepository

    private val _searchProducts = mutableStateOf<List<Product>>(emptyList())    //список средст, которые сейчас ищет пользователь
    val searchProducts: State<List<Product>>  = _searchProducts

    private val _userProducts = mutableStateOf<List<Product>>(emptyList()) //список добавленных пользователем средств (постоянные)
    val userProducts: State<List<Product>> = _userProducts

    private val _isSearching = mutableStateOf(false)    //ViewModel может изменять значение
    val isSearching: State<Boolean> = _isSearching            //UI может только читать значение

    /*закрытое поля MutableStateMapOf  ключ которого — пара (dayIndex, routineName),
     а значение — Set ID продуктов, отмеченных в этот день/рутину.
     */
    private val _usedProductsMap =  mutableStateMapOf<Pair<LocalDate, String>, Set<Int>>()
    //только для чтения извне
    val usedProductsMap: Map<Pair<LocalDate, String>, Set<Int>> get() = _usedProductsMap

    private val _personalInfo = mutableStateOf<SelectedOptions>(SelectedOptions("", "", "", "", ""))
    val personalInfo: State<SelectedOptions> = _personalInfo

    private val _schedule = mutableStateOf<List<Routine>>(emptyList())
    val schedule: State<List<Routine>> = _schedule

    fun generateSchedule(
        products: List<Product>,
        startDate: LocalDate = LocalDate.now()
    ) {
        val week = (0L until 7L).map { startDate.plusDays(it) }
        val suited = products.filter { p ->
            (p.recommendedForSkinTypes!!.contains(personalInfo.value.selectedSkinType) ||
                    p.recommendedForSkinTypes.contains("Все"))
        }

        val morning = suited.filter { it.recommendedTime!!.contains("Утро") }
        val evening = suited.filter { it.recommendedTime!!.contains("Вечер") }

        val morningPlan = distributeOverWeek(morning, week)
        val eveningPlan = distributeOverWeek(evening, week)
         val routines = week.map { date ->
            Routine(
                date = date,
                morning = morningPlan.filterValues { date in it }.keys.toList(),
                evening = eveningPlan.filterValues { date in it }.keys.toList()
            )
        }

        _schedule.value = routines
    }
//    private fun filterByAge(routines: List<Routine>): List<Routine> {
//        val selectedAge = _personalInfo.value.selectedAge
//        routines.forEach { routine ->
//            // 1) Определяем, как именно будем фильтровать одну Routine
//            if (selectedAge == "менее 20") {
//                // Если утром больше 3 продуктов — отбрасываем Eye Cream и Toner
//                val morning = routine.morning
//                val evening = routine.evening
//                if (morning.size > 3 || evening.size > 3 ) {
//                    // если в утренней рутине есть запретные продукты — помечаем эту routine как неподходящую
//                    morning.none { it.name.contains("Eye Cream") || it.name.contains("Toner") }
//                    evening.none { it.name.contains("Eye Cream") || it.name.contains("Toner") }
//                }
//                routine.morning = morning
//                routine.evening = evening
//            }
//        }
//        return routines
//    }

    private val _usageStats = mutableStateOf(
        UsageStats(
            lastWeek = emptyMap(),
            last2Weeks = emptyMap(),
            lastMonth = emptyMap()
        )
    )
    val usageStats: State<UsageStats> = _usageStats

    private val _usageRepo = UsageRepository(ProductsDatabase.getDatabase(application).myDao())

//    private val ip = "172.20.10.8"
    private val localIP = "10.0.2.2"

    fun fetchProducts(searchText: String) {                    //метод поиска всех средств по названию
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response: List<Product> = KtorClient.client
                        .get("http://$localIP:8080/api/products")
                        .body() //Получаем тело запроса
                    Log.d("response size", "${response.size}")
                    _searchProducts.value = response.filter { it.name!!.contains(searchText, ignoreCase = true) }.map { it.copy() }   //оставляем только подходящие по имени средства
                    Log.d("filtered products", "size: ${_searchProducts.value.size}")
                    _isSearching.value = true   /*изменяем значение закрытого поля, чтобы понимать,
                    какой список выводить на экран
                    */
                }
                catch (e: Exception) {
                    Log.e("Не получилось сделать запрос","$e")
                }
            }
        }

    init {  //инициализация полей, отвечающих за дао и репозиторий
        val productDao = ProductsDatabase.getDatabase(application).myDao()
        repository = ProductsRepository(productDao)
        loadUsedProductsMap(context = context)
    }
    fun saveProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }
    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.getAllProducts()
                _userProducts.value = products
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Ошибка при получении продуктов", e)
            }
        }
    }

    //метод записи времени использования средства
    fun markProductUsedAnalytic(productId: Int) = viewModelScope.launch {
        _usageRepo.logUsage(productId)
    }

    //метод для загрузки статистики за разные периоды
    fun loadUsageStats() = viewModelScope.launch(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        //заполняем текущее время в миллисекундах
        val weekAgo = now - 7L * 24 * 60 * 60 * 1000
        val twoWeeksAgo = now - 14L * 24 * 60 * 60 * 1000
        val monthAgo = now - 30L * 24 * 60 * 60 * 1000

        //списки всех использованныз средств за определенные промежутки
        val lastWeek = _usageRepo.getUsageCounts(weekAgo)
        val last2Weeks = _usageRepo.getUsageCounts(twoWeeksAgo)
        val lastMonth = _usageRepo.getUsageCounts(monthAgo)

        _usageStats.value = UsageStats(
            lastWeek = lastWeek,
            last2Weeks = last2Weeks,
            lastMonth = lastMonth
        )
    }

    fun markProductsAsUsed(productId: Int, routine: String, dayIndex: LocalDate, context: Context) {
        val key = dayIndex to routine
        val currentSet = _usedProductsMap[key]?.toMutableSet() ?: mutableSetOf()
        currentSet.add(productId)
        _usedProductsMap[key] = currentSet
        viewModelScope.launch { saveUsedProductsMap(context = context) }
        markProductUsedAnalytic(productId)
    }

    fun unmarkProductsAsUsed(productId: Int, routine: String, dayIndex: LocalDate, context: Context) {
        val key = dayIndex to routine
        val current = _usedProductsMap[key]?.toMutableSet() ?: return
        current.remove(productId)
        _usedProductsMap[key] = current // ← опять пересохраняем, чтобы вызвать recomposition
        viewModelScope.launch { saveUsedProductsMap(context) }
    }

    fun updatePersonalInfo(
        gender: String,
        age: String,
        skinType: String,
        climate: String,
        sunExposure: String
    ) {
        _personalInfo.value = SelectedOptions(
            selectedGender = gender,
            selectedAge = age,
            selectedSkinType = skinType,
            selectedClimate = climate,
            selectedSunExposure = sunExposure
        )
    }


    fun isProductUsed(productId: Int, routine: String, dayIndex: LocalDate): Boolean {
        return _usedProductsMap[dayIndex to routine]?.contains(productId) == true
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProductById(id)
        }
    }

    suspend fun saveUsedProductsMap(context: Context) {
        context.dataStore.edit { preferences ->
            usedProductsMap.forEach { (key, valueSet) ->
                val keyString = "${key.first},${key.second}"
                preferences[stringSetPreferencesKey(keyString)] = valueSet.map {
                    it.toString()
                }.toSet()
            }
        }
    }

    private fun loadUsedProductsMap(context: Context) {
        viewModelScope.launch {
            context.dataStore.data.firstOrNull()?.let { preferences ->
                val restoredMap = mutableMapOf<Pair<LocalDate, String>, Set<Int>>()
                preferences.asMap().forEach { (prefKey, stringSet) ->
                    val keyParts = prefKey.name.split(",")
                    if (keyParts.size == 2 && stringSet is Set<*>) {
                        val date = runCatching { LocalDate.parse(keyParts[0]) }.getOrNull() ?: return@forEach
                        val time = keyParts[1]
                        val productIds = (stringSet as Set<String>).mapNotNull { it.toIntOrNull() }.toSet()
                        restoredMap[date to time] = productIds
                    }
                }
                _usedProductsMap.clear()
                _usedProductsMap.putAll(restoredMap)
            }
        }
    }

    fun clearSearch() { //метод отслеживания поиска средств, если пользователь завершил поиск, вызываем метод
        _searchProducts.value = emptyList()
        _isSearching.value = false
    }

    fun startSearch() {
        _isSearching.value = true
    }
}