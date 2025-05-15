package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skincareroutineplanner.presentation.screens.discover.composables.AddScreenTopAppBar
import com.example.skincareroutineplanner.presentation.screens.discover.composables.RoutineList
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar


@Composable
fun SearchScreen(
    lambdaBack: () -> Unit,
    lambdaHome: () -> Unit,
    lambdaSettings: () -> Unit,
    lambdaAnalytic: () ->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AddScreenTopAppBar(
            lambdaBack
        )
        RoutineList()
        CustomBottomAppBar(
            lambdaHome,
            {},
            lambdaAnalytic,
            lambdaSettings
        )
    }
}