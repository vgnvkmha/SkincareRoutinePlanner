package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.presentation.screens.analytics.composables.RoutineAnalytic
import com.example.skincareroutineplanner.presentation.screens.analytics.composables.TimeRangeSelector
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar

@Composable
fun AnalyticsScreen(
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaSettings: () -> Unit,
    viewModel: ProductViewModel
) {
    Column {
        TimeRangeSelector()
        RoutineAnalytic(viewModel)
        CustomBottomAppBar(
            lambdaHome,
            lambdaSearch,
            {},
            lambdaSettings
        )
    }
}