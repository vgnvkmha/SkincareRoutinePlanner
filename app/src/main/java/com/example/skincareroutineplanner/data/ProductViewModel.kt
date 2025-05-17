package com.example.skincareroutineplanner.data

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductsRepository
    private val _products = mutableStateOf<List<Product>>(emptyList())
    private val ip = "172.20.10.8"
    private val localIP = "10.0.2.2"
    val products  = _products
    fun fetchProducts(searchText: String) : List<Product> {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response: List<Product> = KtorClient.client
                        .get("http://$localIP:8080/api/products")
                        .body()
                    _products.value = response.filter { searchText in it.name }

                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        return _products.value
        }

    init {
        val productDao = ProductsDatabase.getDatabase(application).myDao()
        repository = ProductsRepository(productDao)
    }
    fun saveProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }
    fun getAllProducts(): List<Product> {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.getAllProducts()
        }
        return products.value
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProductById(id)
        }
    }

}