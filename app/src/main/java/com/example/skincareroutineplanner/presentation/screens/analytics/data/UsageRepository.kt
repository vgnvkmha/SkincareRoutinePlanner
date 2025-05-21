package com.example.skincareroutineplanner.presentation.screens.analytics.data

import com.example.skincareroutineplanner.data.MyDao
import kotlin.math.sin

class UsageRepository(private val dao: MyDao) {
    suspend fun logUsage(productId: Int) {
        dao.insertUsageEvent(
            UsageEvent(
                productId = productId,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun getUsageCounts(since: Long) : Map<Int, Int> =
        dao.getUsageCountsSince(since)
            .associate { it.productId to it.count }
}