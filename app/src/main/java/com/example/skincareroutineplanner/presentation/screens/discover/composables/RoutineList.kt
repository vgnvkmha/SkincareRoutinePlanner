package com.example.skincareroutineplanner.presentation.screens.discover.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun RoutineList(){
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Добавить средства в рутину", modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.width(90.dp))
            IconButton(enabled = true, onClick = {},) {
                Icon(Icons.Default.Add, contentDescription = "Добавить средство")
            }

        }
    }
}