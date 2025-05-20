package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomTopAppBar
import com.example.skincareroutineplanner.presentation.screens.home.composables.ScheduleList



@Composable
fun HomeScreen(
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaAnalytics: () -> Unit,
    lambdaSettings: () -> Unit,
    productViewModel: ProductViewModel
) {
    //TODO добавить логику получения streakCount, streakCountBest
    val streakCount = 0
    val streakCountBest = 0
    Column(
        modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            streakCount,
            streakCountBest
        )
        ScheduleList(productViewModel)
        CustomBottomAppBar(
            lambdaHome,
            lambdaSearch,
            lambdaAnalytics,
            lambdaSettings
        )
    }
}