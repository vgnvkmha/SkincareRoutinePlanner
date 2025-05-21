package com.example.skincareroutineplanner.presentation.screens.analytics.data

//DTO, чтобы Room знал, как маппить результаты GROUP BY
data class UsageCount(
    val productId: Int,
    val count: Int
)
