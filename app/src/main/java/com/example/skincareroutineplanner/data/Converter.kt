package com.example.skincareroutineplanner.data

import androidx.room.TypeConverter


/*Класс нужен для конвертирования List<String> в String и обратно
так как Room не умеет это делать самостоятельно
 */
class Converter {
    @TypeConverter
    fun fromList(value: List<String>?) : String {
        return value?.joinToString(separator = " ") ?: ""
    }

    @TypeConverter
    fun toList(value:String) : List<String> {
        return if (value.isEmpty()) emptyList() else value.split(" ")
    }
}