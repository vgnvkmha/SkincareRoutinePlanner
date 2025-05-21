package com.example.skincareroutineplanner.presentation.screens.analytics.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usage_events")
data class UsageEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    val timestamp: Long,
)
