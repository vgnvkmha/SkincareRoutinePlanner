package com.example.skincareroutineplanner.data

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductsRepository

    private val _searchProducts = mutableStateOf<List<Product>>(emptyList())    //список средст, которые сейчас ищет пользователь
    val searchProducts: State<List<Product>>  = _searchProducts

    private val _userProducts = mutableStateOf<List<Product>>(emptyList()) //список добавленных пользователем средств (постоянные)
    val userProducts: State<List<Product>> = _userProducts

    private val _isSearching = mutableStateOf(false)    //ViewModel может изменять значение
    val isSearching: State<Boolean> = _isSearching            //UI может только читать значение

    private val ip = "172.20.10.8"
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
                    Log.e("Не получилось сделать запрос","AAAAAA")
                }
            }
        }

    init {  //инициализация полей, отвечающих за дао и репозиторий
        val productDao = ProductsDatabase.getDatabase(application).myDao()
        repository = ProductsRepository(productDao)
    }
    fun saveProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }
    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = repository.getAllProducts().value ?: emptyList()
                _userProducts.value = products
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Ошибка при получении продуктов", e)
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProductById(id)
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