package com.example.skincareroutineplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "myProducts")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String
)
