package com.example.skincareroutineplanner.domain.schedule

//fun generateSchedule(
//    profile: SelectedOptions,
//    products: List<Product>,
//    startDate: LocalDate = LocalDate.now()
//) : List<Routine> {
//    val week = (0L until 7L).map { startDate.plusDays(it) }
//
//    val suited = products.filter { p ->
//        p.recommendedForSkinTypes!!.contains(profile.selectedSkinType) ?: true
//    }
//
//    val morning = suited.filter { it.recommendedTime.contains("Утро") }
//    val evening = suited.filter { it.recommendedTime.contains("Вечер") }
//
//    val morningPlan = distributeOverWeek(morning, week)
//
//    val eveningPlan = distributeOverWeek(evening, week)
//
//    //Собираем рутину на день
//    return week.map { date ->
//        Routine(
//            date = date,
//            morning = morningPlan.filterValues { date in it }.keys.toList(),
//            evening = eveningPlan.filterValues { date in it }.keys.toList()
//        )
//    }
//}