package com.example.skincareroutineplanner.presentation.screens.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.presentation.screens.discover.composables.RoutineList
import com.example.skincareroutineplanner.presentation.screens.home.composables.CustomBottomAppBar
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Surface


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    lambdaBack: () -> Unit,
    lambdaHome: () -> Unit,
    lambdaSettings: () -> Unit,
    lambdaAnalytic: () -> Unit,
    productViewModel: ProductViewModel
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()
        .background(Background)) {

        // 🔍 Ввод запроса и кнопка поиска
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Введите название средства") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Surface,
                    unfocusedContainerColor = Surface,
                    disabledContainerColor = Surface,
                    cursorColor = OnSurface,
                    focusedTextColor = OnSurface,
                    unfocusedTextColor = OnSurface,
                    focusedPlaceholderColor = OnSurface.copy(alpha = 0.5f),
                    unfocusedPlaceholderColor = OnSurface.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            )
            Spacer(modifier = Modifier.width(8.dp).background(Background))
            IconButton(modifier = Modifier
                .size(56.dp)
                .background(Surface, shape = CircleShape), onClick = {
                if (searchQuery.isNotBlank()) {
                    productViewModel.fetchProducts(searchQuery)
                }
            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "кнопка поиска средства",
                    tint = OnSurface)
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
