package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.skincareroutineplanner.presentation.screens.DarkGreen
import com.example.skincareroutineplanner.presentation.screens.LightGray
import com.example.skincareroutineplanner.presentation.screens.MyRed


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = LightGray,
            titleContentColor = DarkGreen,
            actionIconContentColor = Color.Black,
            scrolledContainerColor = MyRed,
            navigationIconContentColor = MyRed
        ),
        title = { Text(text = "Skincare Routine Tracker", style = MaterialTheme.typography.titleLarge.copy(
            color = DarkGreen
        )) },
        navigationIcon = {IconButton(enabled = true,
            onClick = { TODO("Добавить развертвывание информации о streak (что-то типа fragment)") }) {
            Icon(Icons.Default.Favorite, contentDescription = "streak icon") //TODO("заменить иконку на огонёк")
        } },
        actions = {

            IconButton(enabled = true,
                onClick = {}) {
                Icon(Icons.Default.DateRange, contentDescription = "calendar icon")
            }
        }
    )
}