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
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageRepository
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageStats
import com.example.skincareroutineplanner.presentation.screens.settings.composables.SelectedOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

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
    private val _usedProductsMap =  mutableStateMapOf<Pair<Int, String>, Set<Int>>()
    //только для чтения извне
    val usedProductsMap: Map<Pair<Int, String>, Set<Int>> get() = _usedProductsMap

    private val _personalInfo = mutableStateOf<SelectedOptions>(SelectedOptions("", "", "", "", ""))
    val personalInfo: State<SelectedOptions> = _personalInfo

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
                    _searchProducts.value = response.filter { it.name.contains(searchText, ignoreCase = true) }.map { it.copy() }   //оставляем только подходящие по имени средства
                    Log.d("filtered products", "size: ${_searchProducts.value.size}")
                    _isSearching.value = true   /*изменяем значение закрытого поля, чтобы понимать,
                    какой список выводить на экран
                    */
                }
                catch (e: Exception) {
                    Log.e("Не получилось сделать запрос","Request is failed")
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

    fun markProductsAsUsed(productId: Int, routine: String, dayIndex: Int, context: Context) {
        val key = dayIndex to routine
        val currentSet = _usedProductsMap[key]?.toMutableSet() ?: mutableSetOf()
        currentSet.add(productId)
        _usedProductsMap[key] = currentSet
        viewModelScope.launch { saveUsedProductsMap(context = context) }
        markProductUsedAnalytic(productId)
    }

    fun unmarkProductsAsUsed(productId: Int, routine: String, dayIndex: Int, context: Context) {
        val key = dayIndex to routine
        val current = _usedProductsMap[key]?.toMutableSet() ?: return
        current.remove(productId)
        _usedProductsMap[key] = current // ← опять пересохраняем, чтобы вызвать recomposition
        viewModelScope.launch { saveUsedProductsMap(context) }
    }

    fun updatePersonalInfo(
        gender: String = _personalInfo.value.selectedGender,
        age: String = _personalInfo.value.selectedAge,
        skinType: String = _personalInfo.value.selectedSkinType,
        climate: String = _personalInfo.value.selectedClimate,
        sunExposure: String = _personalInfo.value.selectedSunExposure,
    ) {
        _personalInfo.value = SelectedOptions(
            selectedGender = gender,
            selectedAge = age,
            selectedSkinType = skinType,
            selectedClimate = climate,
            selectedSunExposure = sunExposure
        )
    }


    fun isProductUsed(productId: Int, routine: String, dayIndex: Int): Boolean {
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
                val restoredMap = mutableMapOf<Pair<Int, String>, Set<Int>>()
                preferences.asMap().forEach { (prefKey, stringSet) ->
                    val keyParts = prefKey.name.split(",") // ← ты сохраняешь через запятую, не "_"
                    if (keyParts.size == 2 && stringSet is Set<*>) {
                        val day = keyParts[0].toIntOrNull() ?: return@forEach
                        val time = keyParts[1]
                        val productIds = (stringSet as Set<String>).mapNotNull { it.toIntOrNull() }.toSet()
                        restoredMap[day to time] = productIds
                    }
                }
                _usedProductsMap.clear()
                _usedProductsMap.putAll(restoredMap) // ← обновляем stateMap напрямую
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