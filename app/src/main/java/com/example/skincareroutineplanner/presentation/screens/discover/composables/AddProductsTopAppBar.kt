@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skincareroutineplanner.presentation.screens.discover.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily


@Preview(showBackground = true)
@Composable
fun AddScreenTopAppBar() {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = PrimaryContainer,
            titleContentColor = OnPrimaryContainer,
            actionIconContentColor = OnPrimaryContainer,
            scrolledContainerColor = Surface,
            navigationIconContentColor = OnPrimaryContainer
        ),
        title = { Text(text = "Добавить средство", modifier = Modifier.padding(16.dp),
            fontFamily = mainFontFamily, fontWeight = FontWeight.Black
        ) },
        navigationIcon = {
            IconButton(enabled = true, onClick = {
                TODO("Добавить возвращение на экран со всеми средствами" +
                        "пользователя") }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Возвращение на " +
                        "экран со средствами")
            }
        },
        actions = {
            IconButton(enabled = true, onClick = {

            }) {
                Icon(Icons.Default.Search, contentDescription = "кнопка добавления средства")
            }
        }
    )
}