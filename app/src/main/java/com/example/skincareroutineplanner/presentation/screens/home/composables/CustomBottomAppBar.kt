package com.example.skincareroutineplanner.presentation.screens.home.composables

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnBackground



@Composable
fun CustomBottomAppBar(
    lambdaHome: () -> Unit,
    lambdaSearch: () -> Unit,
    lambdaAnalytics: () -> Unit,
    lambdaSettings: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        containerColor = Background,
        contentColor = OnBackground,
        actions = {
            IconButton(modifier = Modifier.weight(1f),
                onClick = lambdaHome) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
            IconButton(modifier = Modifier.weight(1f),
                onClick = lambdaSearch) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
            IconButton(modifier = Modifier.weight(1f),
                onClick = lambdaAnalytics) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_monitoring),
                    contentDescription = "Analytic"
                )
            }
            IconButton(modifier = Modifier.weight(1f),
                onClick = lambdaSettings) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    )
}