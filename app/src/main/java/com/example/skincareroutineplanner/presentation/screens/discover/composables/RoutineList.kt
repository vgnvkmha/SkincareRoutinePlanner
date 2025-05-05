package com.example.skincareroutineplanner.presentation.screens.discover.composables


import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.OnSecondaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.SecondaryContainer
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Preview(showBackground = true)
@Composable
fun RoutineList(){
    val items = listOf("A","B","C","D")
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth().background(color = SecondaryContainer,
            shape = RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.spacedBy(25.dp)) {
            Text(text = "Твои средства", modifier = Modifier.padding(16.dp),
                fontFamily = mainFontFamily, fontWeight = FontWeight.Black,
                fontSize = 20.sp, color = OnSecondaryContainer)
            Spacer(modifier = Modifier.width(110.dp))
            IconButton(enabled = true, onClick = {}, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(Icons.Default.Add, contentDescription = "Добавить средство",
                    modifier = Modifier.size(48.dp))
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(items) {item->
                    Card(modifier = Modifier.size(192.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Surface)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "изображение средства",
                            modifier = Modifier.fillMaxWidth().height(150.dp))
                        Text(text = "Название средства", fontFamily = mainFontFamily,
                            fontWeight = FontWeight.Normal, color = OnSurface, modifier = Modifier
                            .align(Alignment.CenterHorizontally))
                    }
                }
            }
        )
    }
}