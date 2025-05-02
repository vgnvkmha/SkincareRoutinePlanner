package com.example.skincareroutineplanner.presentation.screens.discover.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Preview(showBackground = true)
@Composable
fun RoutineList(){
    val items = listOf("A","B","C")
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Добавить средства в рутину", modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.width(90.dp))
            IconButton(enabled = true, onClick = {},) {
                Icon(Icons.Default.Add, contentDescription = "Добавить средство")
            }

        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()) {
                items(items) { component->
                    Row(modifier = Modifier.height(48.dp).fillMaxWidth().padding(4.dp)) {
                        Icon(painter = painterResource(id = R.drawable.ic_launcher_background),
                            modifier = Modifier.size(48.dp),
                            contentDescription = "изображение компонента")
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "Name")
                            Text(text = "Description AAAAAAAAAAAAAAAAAA", fontFamily = mainFontFamily,
                                fontWeight = FontWeight.Black)
                        }
                    }
            }
        }
    }
}