package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar
import com.example.skincareroutineplanner.presentation.screens.settings.composables.SettingsList
import com.example.skincareroutineplanner.ui.theme.Background

@Composable
fun SettingsScreen(
    lambdaProducts: () -> Unit,
    lambdaSettings: () -> Unit,
    lambdaAbout: () -> Unit,
    lambdaLogOut: () -> Unit,
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaAnalytic: () -> Unit

) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Background)) {
        SettingsList(lambdaProducts, lambdaSettings, lambdaAbout, lambdaLogOut)
        CustomBottomAppBar(
            lambdaHome = lambdaHome,
            lambdaSearch = lambdaSearch,
            lambdaAnalytics = lambdaAnalytic,
            lambdaSettings = {}
        )
    }
}