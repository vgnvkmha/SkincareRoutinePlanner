package com.example.skincareroutineplanner.domain.schedule

import java.time.LocalDate
import com.example.skincareroutineplanner.data.Product as Product1

fun distributeOverWeek(
    product: List<Product1>,
    days: List<LocalDate>
) : Map<Product1, List<LocalDate>> {
    val result = mutableMapOf<Product1, MutableList<LocalDate>>()
    product.forEach { p->
        //Интервал между использованиями
        val step = days.size.toDouble() / p.usageFrequencyPerWeek!!
        var cursor = 0.0
        repeat(p.usageFrequencyPerWeek) {
            val dayIndex = cursor.toInt().coerceIn(days.indices)
            result.computeIfAbsent(p) { mutableListOf() }.add(days[dayIndex])
            cursor += step
        }
    }
    return result
}