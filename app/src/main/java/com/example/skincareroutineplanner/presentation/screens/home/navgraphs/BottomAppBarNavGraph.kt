package com.example.skincareroutineplanner.presentation.screens.home.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skincareroutineplanner.presentation.screens.home.screens.AnalyticsScreen
import com.example.skincareroutineplanner.presentation.screens.home.screens.HomeScreen
import com.example.skincareroutineplanner.presentation.screens.home.screens.SearchScreen
import kotlinx.serialization.Serializable



@Composable
fun BottomAppBarNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(lambdaHome = {}, //Оставляем лямбду пустой, так как экран один и тот же
                lambdaSearch = { navController.navigate("search") },
                lambdaAnalytics = {navController.navigate("analytic")},
                lambdaSettings = {  })
        }
        composable("search") {
            SearchScreen(
                lambdaBack = {navController.navigate("home")},
                lambdaSearch = {
                    TODO("Добавить экран с поиском средств")
                },
                lambdaHome = {navController.navigate("home")},
                lambdaAnalytic = {navController.navigate("analytic")},
                lambdaSettings = {}
            )

        }
        composable("analytic") {
            AnalyticsScreen(
                lambdaHome = {navController.navigate("home")},
                lambdaSearch = {navController.navigate("search")},
                lambdaSettings = {}
            )
        }
    }
}
