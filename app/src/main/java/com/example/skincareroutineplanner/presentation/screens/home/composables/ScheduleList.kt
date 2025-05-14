package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Preview(showBackground = true)
@Composable
fun ScheduleList() {
    val components = listOf("A", "B", "C", "D")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        // Заголовок с иконкой
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(bottom = 16.dp)
                    ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                tint = Primary,
                contentDescription = "Note Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "0 из 1 задачи завершено",
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        // Основной контейнер
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize().background(
                color = Background,
            )
        ) {

            // Заголовок "Моя рутина"
            Text(
                text = "Моя рутина",
                color = OnPrimaryContainer,
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth().background(
                    color = PrimaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
            )

            // Статистика и кнопка "Отметить всё"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "0 из 10 средств использовано",
                    color = OnSurface,
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Отметить всё",

                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyMedium,
                )
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.done),
                        contentDescription = "Отметить все средства",
                        tint = OnPrimary,
                        modifier = Modifier.size(40.dp).background(Primary)
                    )
                }
            }

            // Список карточек
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxHeight(0.8f).background(Surface)
            ) {
                items(components) { component ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth().height(60.dp)
                            .clickable { /* TODO: отметить элемент */ }
                            ,
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Background
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = component,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.done),
                                    contentDescription = "Пометить как использованное",
                                    tint = OnPrimary,
                                    modifier = Modifier
                                        .size(48.dp).background(Primary)
                                )
                            }
                        }
                    }
                }
            }

            // Кнопка "Пропустить"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: действие по пропуску */ },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryContainer
                )
            ) {
                Text(
                    text = "Пропустить текущую рутину",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}