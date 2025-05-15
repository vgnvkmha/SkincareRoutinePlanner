package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnBackground
import com.example.skincareroutineplanner.ui.theme.Secondary


@Composable
fun CustomBottomAppBar(
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaAnalytics: () -> Unit,
    lambdaSettings: () -> Unit
) {
    val selectedOption = remember { mutableStateOf("") }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), // немного выше для лучшего UX
        containerColor = Background,
        contentColor = OnBackground
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                selectedOption.value = "home"
                lambdaHome()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = if (selectedOption.value == "home") Secondary else OnBackground
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                selectedOption.value = "search"
                lambdaSearch()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = if (selectedOption.value == "search") Secondary else OnBackground
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                selectedOption.value = "analytics"
                lambdaAnalytics()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_monitoring),
                contentDescription = "Analytics",
                tint = if (selectedOption.value == "analytics") Secondary else OnBackground
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                selectedOption.value = "settings"
                lambdaSettings()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = if (selectedOption.value == "settings") Secondary else OnBackground
            )
        }
    }
}