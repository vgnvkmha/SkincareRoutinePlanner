package com.example.skincareroutineplanner.presentation.screens.home.navgraphs

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.data.ProductViewModelFactory
import com.example.skincareroutineplanner.presentation.screens.home.screens.AnalyticsScreen
import com.example.skincareroutineplanner.presentation.screens.home.screens.HomeScreen
import com.example.skincareroutineplanner.presentation.screens.home.screens.SearchScreen
import com.example.skincareroutineplanner.presentation.screens.home.screens.SettingsScreen
import com.example.skincareroutineplanner.presentation.screens.settings.composables.myProducts.MyProducts
import com.example.skincareroutineplanner.presentation.screens.settings.composables.userSettings.PersonSettings
import java.time.LocalDate


@Composable
fun BottomAppBarNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(application, context)
    )
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(lambdaHome = {}, //Оставляем лямбду пустой, так как экран один и тот же
                lambdaSearch = { navController.navigate("search") },
                lambdaAnalytics = {navController.navigate("analytic")},
                lambdaSettings = {navController.navigate("settings")},
                productViewModel = viewModel,
                context = context)
        }
        composable("search") {
            SearchScreen(
                lambdaBack = {navController.popBackStack()},
//                lambdaSearch = {
//                    TODO("Добавить экран с поиском средств")
//                },
                lambdaHome = {navController.navigate("home")},
                lambdaAnalytic = {navController.navigate("analytic")},
                lambdaSettings = {navController.navigate("settings")},
                productViewModel = viewModel
            )

        }
        composable("analytic") {
            AnalyticsScreen(
                lambdaHome = {navController.navigate("home")},
                lambdaSearch = {navController.navigate("search")},
                lambdaSettings = {navController.navigate("settings")},
                viewModel = viewModel
            )
        }
        composable("settings") {
            SettingsScreen(
                lambdaProducts = {navController.navigate("userProducts")},
                lambdaSettings = {navController.navigate("userSettings")},
                lambdaLogOut = {},
                lambdaHome = {navController.navigate("home")},
                lambdaSearch = {navController.navigate("search")},
                lambdaAnalytic = {navController.navigate("analytic")},
                lambdaCreateSchedule = {
                    viewModel.generateSchedule(viewModel.userProducts.value, LocalDate.now())
                }
            )
        }
        composable("userSettings") {
            PersonSettings(
                onBackClick = {
//                    viewModel.updatePersonalInfo(
//                        gender =
//                    )
                    navController.popBackStack()},
                productViewModel = viewModel,
                context = context
            )
        }
        composable("userProducts") {
            MyProducts(
                productViewModel = viewModel,
                lambdaNavigateToSearch = {navController.navigate("search")}
            )
        }
    }
}
