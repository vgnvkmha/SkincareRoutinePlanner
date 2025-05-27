package com.example.skincareroutineplanner.domain.schedule

import com.example.skincareroutineplanner.data.Product
import java.time.LocalDate

data class Routine(
    val date: LocalDate,
    var morning: List<Product>,
    var evening: List<Product>
)
