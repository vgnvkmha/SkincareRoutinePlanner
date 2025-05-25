package com.example.skincareroutineplanner.presentation.screens.analytics.data

import com.example.skincareroutineplanner.data.MyDao

class UsageRepository(private val dao: MyDao) {
    suspend fun logUsage(productId: Int) {
        dao.insertUsageEvent( //вызываем метод, чтобы добавить новую запись в таблицу usage_events
            UsageEvent(
                productId = productId,
                timestamp = System.currentTimeMillis() //текущее время, чтобы потом сортировать по дате
            )
        )
    }

    //функция, которая возвращает количество использований средства начиная с since
    suspend fun getUsageCounts(since: Long) : Map<Int, Int> = //мапа productId -> count
        dao.getUsageCountsSince(since)
            .associate { it.productId to it.count } //превращает список DTO в Map<Int, Int>
}