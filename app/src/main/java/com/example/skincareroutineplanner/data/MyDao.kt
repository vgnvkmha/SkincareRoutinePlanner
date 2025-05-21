package com.example.skincareroutineplanner.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageCount
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageEvent


@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Query("SELECT * FROM myProducts ORDER BY id ASC")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM myProducts WHERE id=:id LIMIT 1")
    suspend fun getProductById(id:Int) : Product?

    @Query("DELETE FROM myProducts")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM myProducts WHERE id=:id")
    suspend fun deleteProductById(id: Int)

    //для usage_table

    @Insert
    suspend fun insertUsageEvent(event: UsageEvent)

    @Query("SELECT productId AS productId, COUNT(*) AS count FROM usage_events WHERE timestamp >= :since GROUP BY productId")
    suspend fun getUsageCountsSince(since: Long) : List<UsageCount>
}