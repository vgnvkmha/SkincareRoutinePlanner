package com.example.skincareroutineplanner.presentation.screens.home.composables

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.presentation.screens.discover.composables.ImageAlertDialog
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.Error
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Outline
import com.example.skincareroutineplanner.ui.theme.OutlineDark
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryDark
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily
import java.time.LocalDate

@SuppressLint("AutoboxingStateCreation") // подавляем предупреждение о состоянии Int
@Composable
fun ScheduleList(
    productViewModel: ProductViewModel,
) {

    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }

    val allProducts: List<Product> = productViewModel.userProducts.value

    LaunchedEffect(allProducts) {
        allProducts.forEach {
            Log.d("DEBUG", "Product: ${it.name}, Time: ${it.recommendedTime}")
        }
    }

    // Состояние выбранной рутины: "Утро" или "Вечер"
    var selectedRoutine by remember { mutableStateOf("Утро") }

    val selectedProducts by remember(allProducts, selectedRoutine) {
        mutableStateOf(
            allProducts.filter { it.recommendedTime.contains(selectedRoutine) }
        )
    }

    val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    val currentDayIndex = LocalDate.now().dayOfWeek.value % 7


    // Состояние выбранного дня недели (0..6)
    var selectedDayIndex by remember { mutableIntStateOf(currentDayIndex) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(720.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), // отступы внутри колонки
        verticalArrangement = Arrangement.spacedBy(12.dp) //отступ между элементами по вертикали
    ) {
        // Кнопки-переключатели "Утро" / "Вечер"
        Row(
            modifier = Modifier.fillMaxWidth(),
            //оступы от краев и друг от друга максимальны и равны
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Утро", "Вечер").forEach { routine ->
                OutlinedButton( //кнопка чаще всего используется без заливки внутри
                    onClick = { selectedRoutine = routine },
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, PrimaryDark), //цвет рамки
                    colors = ButtonDefaults.outlinedButtonColors(
                        // выделение активной кнопки
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

        // Прокручиваемый ряд дней недели
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            // вертикальные отступы внутри LazyRow
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            //как items() только позволяет получать индекс элемента
            itemsIndexed(daysOfWeek) { index, day ->
                val isSelected = index == selectedDayIndex
                OutlinedButton(
                    onClick = { selectedDayIndex = index },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        // выделение текущего дня
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

        // Заголовок списка рутины
        Text(
            text = "Моя рутина",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        // Список средств ухода (LazyColumn с ограниченной высотой под 6 карточек)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) //займет все доступное место
                .background(Surface),  // задний фон для разделения карточек
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedProducts) { product ->
                val isUsed = productViewModel.isProductUsed(product.id)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // тень
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUsed) Outline  else  Background// фон карточки
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), // внутренние отступы карточки
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
//                        ImageAlertDialog(product, height = 68)
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
                                if (productViewModel.isProductUsed(product.id)) {
                                    productViewModel.unmarkProductsAsUsed(product.id)
                                } else {
                                    productViewModel.markProductsAsUsed(product.id)
                                }
                            /* TODO: логика отметки средства */ },
                            modifier = Modifier
                                .size(36.dp)
                                .background(if (isUsed) Outline  else  PrimaryContainer, CircleShape) // круглая кнопка
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.done),
                                contentDescription = "Пометить как использованное",
                                tint = OnPrimary // цвет иконки внутри
                            )
                        }
                    }
                }

            }
        }

        // Кнопка "Пропустить текущую рутину"
        OutlinedButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.error), // красная обводка
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Error // цвет текста и иконок внутри
            )
        ) {
            Text(
                text = "Пропустить текущую рутину",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}