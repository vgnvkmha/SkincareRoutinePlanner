package com.example.skincareroutineplanner.presentation.screens.discover.composables


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@SuppressLint("SuspiciousIndentation")
@Preview(showBackground = true)
@Composable
fun RoutineList(){
    val items = listOf("A","B","C","D")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
                .height(750.dp),
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