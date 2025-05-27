package com.example.skincareroutineplanner.domain.schedule

import com.example.skincareroutineplanner.data.Product
import java.time.LocalDate

data class Routine(
    val date: LocalDate,
    val morning: List<Product>,
    val evening: List<Product>
)
