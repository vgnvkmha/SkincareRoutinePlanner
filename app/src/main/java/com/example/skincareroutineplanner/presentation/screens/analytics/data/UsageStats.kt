package com.example.skincareroutineplanner.presentation.screens.analytics.data

data class UsageStats(
    val lastWeek: Map<Int, Int>,
    val last2Weeks: Map<Int, Int>,
    val lastMonth: Map<Int, Int>
)
