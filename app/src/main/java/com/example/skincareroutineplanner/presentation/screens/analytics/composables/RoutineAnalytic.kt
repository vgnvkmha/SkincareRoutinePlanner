package com.example.skincareroutineplanner.presentation.screens.analytics.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.SecondaryContainer
import com.example.skincareroutineplanner.ui.theme.SecondaryDark
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Preview(showBackground = true)
@Composable
fun RoutineAnalytic() {
    val items = listOf("Название A", "Название B", "Название C", "Название D")
    Column(modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Статистика", fontSize = 30.sp, fontFamily = mainFontFamily,
            fontWeight = FontWeight.Black)
        Row(modifier = Modifier.fillMaxWidth().height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(modifier = Modifier.size(30.dp).align(Alignment.CenterVertically)
                .graphicsLayer (alpha = 0.99f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Yellow, Color.Magenta)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                },
                painter = painterResource(id = R.drawable.ic_morning_icon),
                contentDescription = "изображение солнца")
            Text(text = "Утренняя рутина",
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically))
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().height(276.dp)) {
            items(items) {
                component ->
                Column (modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                    .height(70.dp)) {
                    Text(text = component, fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Black)
                    Text(text = "количество использований в неделю")
                    Text(text = "на сколько процентов повысилось/понизилось использование",
                        color = Primary)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(modifier = Modifier.size(30.dp).align(Alignment.CenterVertically)
                .graphicsLayer (alpha = 0.99f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(SecondaryContainer, SecondaryDark)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                },
                painter = painterResource(id = R.drawable.ic_evening_icon),
                contentDescription = "изображение солнца")
            Text(text = "Вечерняя рутина",
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterVertically))

        }
        LazyColumn(modifier = Modifier.fillMaxWidth().height(350.dp)) {
            items(items) {
                    component ->
                Column (modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                    .height(70.dp)) {
                    Text(text = component, fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Black)
                    Text(text = "количество использований в неделю")
                    Text(text = "на сколько процентов повысилось/понизилось использование",
                        color = Primary)
                }
            }
        }

    }
}