package com.example.skincareroutineplanner.presentation.screens.home.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skincareroutineplanner.presentation.screens.home.screens.HomeScreen
import kotlinx.serialization.Serializable


@Suppress("INLINE_FROM_HIGHER_PLATFORM")
@Composable
fun BottomAppBarNavigation() {
    val homeScreen = "HomeScreen"
    val searchScreen = "SearchScreen"
    val analyticsScreen = "AnalyticsScreen"
    val settingsScreen = "SettingsScreen"
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreen(lambdaHome = {}, //Оставляем лямбду пустой, так как экран один и тот же
                lambdaSearch = { navController.navigate(SearchScreen) },
                lambdaAnalytics = {navController.navigate(AnalyticsScreen)},
                lambdaSettings = { navController.navigate(SettingsScreen) })
        }
        composable<SearchScreen> {

        }
    }
}

//Home, Search, Analytics, Settings
@Serializable
object HomeScreen

@Serializable
object SearchScreen

@Serializable
object AnalyticsScreen

@Serializable
object SettingsScreen