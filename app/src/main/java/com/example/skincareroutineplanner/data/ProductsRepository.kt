package com.example.skincareroutineplanner.data

import androidx.lifecycle.LiveData

class ProductsRepository(private val dao: MyDao) {
    // Получение всех продуктов
    suspend fun getAllProducts(): LiveData<List<Product>> {
        return dao.getAllProducts()
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