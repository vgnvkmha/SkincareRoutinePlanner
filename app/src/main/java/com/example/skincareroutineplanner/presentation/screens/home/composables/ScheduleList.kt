package com.example.skincareroutineplanner.presentation.screens.home.composables

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.domain.schedule.Routine
import com.example.skincareroutineplanner.domain.schedule.prettySchedule
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Outline
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryDark
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily
import okhttp3.internal.notify
import java.time.LocalDate

@SuppressLint("AutoboxingStateCreation")
@Composable
fun ScheduleList(
    productViewModel: ProductViewModel,
    context: Context
) {
    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }

    //Скорее всего, ошибка в этом методе, я хочу спать
//    productViewModel.updatePersonalInfo()
    Log.d("personal info:","${productViewModel.personalInfo.value}")
    val schedule: List<Routine> = prettySchedule( productViewModel.schedule.value, productViewModel.productsTypes)
    schedule.forEach {
        Log.d("date: ", "${it.date}")
        Log.d("products", "${it.morning}")
    }
    val usedProductsMap = productViewModel.usedProductsMap

    val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    val currentDayIndex = LocalDate.now().dayOfWeek.value % 7

    // Выбор "Утро"/"Вечер"
    var selectedRoutine by remember { mutableStateOf("Утро") }
    // Выбор дня недели
    var selectedDayIndex by remember { mutableIntStateOf(currentDayIndex) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(720.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Кнопки "Утро"/"Вечер"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Утро", "Вечер").forEach { routine ->
                OutlinedButton(
                    onClick = { selectedRoutine = routine },
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, PrimaryDark),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (routine == selectedRoutine) PrimaryContainer else Color.Transparent
                    )
                ) {
                    Text(
                        routine,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        // Ряд дней недели
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            itemsIndexed(daysOfWeek) { index, day ->
                val isSelected = index == selectedDayIndex
                OutlinedButton(
                    onClick = { selectedDayIndex = index },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) PrimaryContainer else Color.Transparent
                    )
                ) {
                    Text(
                        text = day,
                        color = if (isSelected) OnPrimaryContainer else OnSurface
                    )
                }
            }
        }

        // Заголовок
        Text(
            text = "Моя рутина",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Log.d("schedule size", "${schedule.size}")
        Log.d("schedule: ", "$schedule")
        // Найдём продукты для выбранного дня и времени суток:
        Log.d("selected day index: ", "$selectedDayIndex")
//        val execProducts = productViewModel.userProducts.value.filter { it.recommendedTime.contains(selectedRoutine) }
        val products: List<Product> = schedule
            .getOrNull(selectedDayIndex)
            ?.let { if (selectedRoutine == "Утро") it.morning else it.evening }
            ?: emptyList()

//        if (products.size == 0) {
//            products = execProducts
//        }

        // Список карточек продуктов
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Surface),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val date = schedule.getOrNull(selectedDayIndex)?.date
            Log.d("Products size", "${products.size}")
            items(products) { product ->

                val isUsed = productViewModel.isProductUsed(product.id, selectedRoutine, date ?: LocalDate.now())
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUsed) Outline else Background
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = mainFontFamily,
                            fontWeight = FontWeight.Black,
                            color = OnSurface,
                            modifier = Modifier.weight(0.9f)
                        )
                        IconButton(
                            onClick = {
                                if (isUsed) {
                                    productViewModel.unmarkProductsAsUsed(product.id, selectedRoutine, date ?: LocalDate.now(), context)
                                } else {
                                    productViewModel.markProductsAsUsed(product.id, selectedRoutine, date ?: LocalDate.now(), context)
                                }
                            },
                            modifier = Modifier
                                .size(36.dp)
                                .background(if (isUsed) Outline else PrimaryContainer, CircleShape)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.done),
                                contentDescription = "Пометить как использованное",
                                tint = OnPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}