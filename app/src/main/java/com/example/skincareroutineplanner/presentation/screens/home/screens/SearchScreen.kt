package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.presentation.screens.discover.composables.AddScreenTopAppBar
import com.example.skincareroutineplanner.presentation.screens.discover.composables.RoutineList
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar


@Composable
fun SearchScreen(
    lambdaBack: () -> Unit,
    lambdaHome: () -> Unit,
    lambdaSettings: () -> Unit,
    lambdaAnalytic: () -> Unit,
    productViewModel: ProductViewModel
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔍 Ввод запроса и кнопка поиска
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Введите название средства") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (searchQuery.isNotBlank()) {
                    productViewModel.fetchProducts(searchQuery)
                }
            }) {
                Text("Поиск")
            }
        }

        // 📦 Список найденных или всех продуктов
        RoutineList(
            productViewModel = productViewModel,
            onBack = lambdaBack
        )

        // 🔻 Нижнее меню
        CustomBottomAppBar(
            lambdaHome,
            {},
            lambdaAnalytic,
            lambdaSettings
        )
    }
}
