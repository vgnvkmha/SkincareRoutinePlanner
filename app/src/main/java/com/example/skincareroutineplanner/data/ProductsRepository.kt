package com.example.skincareroutineplanner.data

import android.util.Log
import androidx.lifecycle.LiveData

class ProductsRepository(private val dao: MyDao) {
    // Получение всех продуктов
    suspend fun getAllProducts(): List<Product> {
        val data = dao.getAllProducts()
        Log.d("Repo", "Loaded from DB: ${data.size}")
        return data
    }
    // Получение одного продукта по ID
    suspend fun getProductById(id: Int): Product? {
        return dao.getProductById(id)
    }
    // Добавление продукта
    suspend fun addProduct(product: Product) {
        dao.addProduct(product)
    }
    // Удаление всех продуктов
    suspend fun deleteAllProducts() {
        dao.deleteAllProducts()
    }
    // Удаление одного продукта по ID
    suspend fun deleteProductById(id: Int) {
        dao.deleteProductById(id)
    }
}