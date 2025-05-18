package com.example.skincareroutineplanner.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Query("SELECT * FROM myProducts ORDER BY id ASC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM myProducts WHERE id=:id LIMIT 1")
    suspend fun getProductById(id:Int) : Product?

    @Query("DELETE FROM myProducts")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM myProducts WHERE id=:id")
    suspend fun deleteProductById(id: Int)
}