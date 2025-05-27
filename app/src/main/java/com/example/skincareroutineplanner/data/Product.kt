package com.example.skincareroutineplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "myProducts")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
    val ingredients: List<String>,
    val recommendedForSkinTypes: List<String>?,
    val usageFrequencyPerWeek: Int,
    val recommendedTime: List<String>,
    val photo_url:String
)
