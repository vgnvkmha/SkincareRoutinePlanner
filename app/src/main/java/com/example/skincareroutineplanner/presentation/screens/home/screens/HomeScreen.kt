package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomTopAppBar
import com.example.skincareroutineplanner.presentation.screens.home.composables.ScheduleList


@Composable
fun HomeScreen(
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaAnalytics: () -> Unit,
    lambdaSettings: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar()
        ScheduleList()
        CustomBottomAppBar(
            lambdaHome,
            lambdaSearch,
            lambdaAnalytics,
            lambdaSettings
        )
    }
}