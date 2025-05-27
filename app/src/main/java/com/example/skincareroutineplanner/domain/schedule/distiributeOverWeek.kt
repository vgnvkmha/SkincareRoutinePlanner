package com.example.skincareroutineplanner.domain.schedule
import com.example.skincareroutineplanner.data.Product
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


// Понадобится функция, чтобы извлечь категорию из имени продукта
private fun categoryOf(name: String, categories: List<String>): String? =
    categories.firstOrNull { name.contains(it, ignoreCase = true) }

// Ищем ближайший к `orig` день из `days`, в котором для данной категории ещё нет вставки
private fun findAlternativeDay(
    orig: LocalDate,
    days: List<LocalDate>,
    schedule: Map<LocalDate, List<Pair<String, *>>>, // Pair<категория, что угодно>
    cat: String
): LocalDate? {
    return days
        .sortedBy { ChronoUnit.DAYS.between(orig, it).absoluteValue }
        .firstOrNull { day ->
            schedule[day]?.none { it.first == cat } ?: true
        }
}

fun distributeOverWeek(
    products: List<Product>,
    days: List<LocalDate>,
    categories: List<String>
): Map<Product, List<LocalDate>> {
    // Шаг 1. Распределяем каждую запись равномерно по неделе
    val temp = mutableMapOf<Product, MutableList<LocalDate>>()
    products.forEach { p ->
        val freq = p.usageFrequencyPerWeek.coerceAtLeast(1)
        val step = if (freq > 1)
            (days.size - 1).toDouble() / (freq - 1)
        else
            0.0

        var cursor = 0.0
        repeat(freq) {
            val idx = cursor.roundToInt().coerceIn(days.indices)
            temp.computeIfAbsent(p) { mutableListOf() }
                .add(days[idx])
            cursor += step
        }
    }

    // Шаг 2. Собираем расписание по дням, чтобы видеть, в каком дне какие категории уже заняты
    // Используем Map<день, List<Pair<категория, продукт>>>
    val scheduleByDay = days.associateWith { mutableListOf<Pair<String, Product>>() }
    temp.forEach { (p, dates) ->
        val cat = categoryOf(p.name, categories)
        dates.forEach { d ->
            scheduleByDay[d]?.add(cat!! to p)
        }
    }

    // Шаг 3. Ищем конфликты: в один день попало >1 записи одной категории
    temp.forEach { (p, dates) ->
        val cat = categoryOf(p.name, categories) ?: return@forEach

        // Нам надо проверить каждую дату, что мы туда поставили
        dates.toList().forEach { d ->
            val entries = scheduleByDay[d]!!.filter { it.first == cat }
            if (entries.size > 1) {
                // лишние (кроме первого) нужно передвинуть
                val extras = entries.drop(1)
                extras.forEach { (_, prod) ->
                    if (prod == p) {
                        // убираем этот день у p
                        temp[p]!!.remove(d)
                        scheduleByDay[d]!!.removeIf { it.second == p && it.first == cat }

                        // ищем альтернативу
                        val alt = findAlternativeDay(d, days, scheduleByDay, cat)
                        if (alt != null) {
                            temp[p]!!.add(alt)
                            scheduleByDay[alt]!!.add(cat to p)
                        } else {
                            // вернуть назад, если некуда сдвинуть
                            temp[p]!!.add(d)
                            scheduleByDay[d]!!.add(cat to p)
                        }
                    }
                }
            }
        }
    }

    return temp
}