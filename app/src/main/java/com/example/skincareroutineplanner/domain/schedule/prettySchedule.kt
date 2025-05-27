package com.example.skincareroutineplanner.domain.schedule

import com.example.skincareroutineplanner.data.Product
import java.time.LocalDate


// Допомогательная структура для сборки расписания
private data class MutableRoutine(
    val date: LocalDate,
    val morning: MutableList<Product> = mutableListOf(),
    val evening: MutableList<Product> = mutableListOf()
)

fun prettySchedule(
    routines: List<Routine>,
    categories: List<String>
): List<Routine> {
    // 1) Инициализируем «пустое» расписание-строитель
    val builders = routines.map { MutableRoutine(it.date) }

    // 2) Проходим по каждому дню и его morning/ evening
    routines.forEachIndexed { dayIdx, original ->
        // ————— Утро —————
        original.morning.forEach { prod ->
            // найдём категорию
            val cat = categories.firstOrNull { prod.name.contains(it, ignoreCase = true) }
            // ищем куда поместить
            val targetDay = (dayIdx until routines.size).firstOrNull { j ->
                // в builders[j].morning ещё нет этого cat
                cat == null || builders[j].morning
                    .none { p -> categories.firstOrNull { c -> p.name.contains(c, ignoreCase = true) } == cat }
            } ?: builders.lastIndex

            builders[targetDay].morning += prod
        }

        // ————— Вечер —————
        original.evening.forEach { prod ->
            val cat = categories.firstOrNull { prod.name.contains(it, ignoreCase = true) }
            val targetDay = (dayIdx until routines.size).firstOrNull { j ->
                cat == null || builders[j].evening
                    .none { p -> categories.firstOrNull { c -> p.name.contains(c, ignoreCase = true) } == cat }
            } ?: builders.lastIndex

            builders[targetDay].evening += prod
        }
    }

    // 3) Превращаем обратно в List<Routine> с неизменяемыми списками
    return builders.map { br ->
        Routine(
            date = br.date,
            morning = br.morning.toList(),
            evening = br.evening.toList()
        )
    }
}