package com.example.skincareroutineplanner.presentation.screens.analytics.composables

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.Error
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.SecondaryContainer
import com.example.skincareroutineplanner.ui.theme.SecondaryDark
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun RoutineAnalytic(
    viewModel: ProductViewModel,   // получаем VM из Hilt / Activity
) {
    // 1) Подпишемся на список пользовательских продуктов и на статистику
    val products by viewModel.userProducts
    val usageStats by viewModel.usageStats

    // 2) При первом запуске загрузим статистику
    LaunchedEffect(Unit) {
        viewModel.loadUsageStats()
    }

    // 3) Порог: за 7 дней половина — это 3 (7/2)
    val weeklyThreshold = 7 / 2

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(750.dp)
            .background(Background)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Статистика за неделю",
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
            fontFamily = mainFontFamily
        )

        // Утренняя рутина
        RoutineSection(
            title = "Утренняя рутина",
            iconRes = R.drawable.ic_morning_icon,
            items = products.map { product ->
                // количество использований за неделю
                val count = usageStats.lastWeek[product.id] ?: 0
                ProductUsageDisplay(product.name, count, weeklyThreshold)
            },
            color = listOf(Color.Yellow, Color.Magenta)
        )

        // Вечерняя рутина
        RoutineSection(
            title = "Вечерняя рутина",
            iconRes = R.drawable.ic_evening_icon,
            items = products.map { product ->
                // в примере используем ту же статистику,
                // при разделении утро/вечер нужна своя логика выборки
                val count = usageStats.lastWeek[product.id] ?: 0
                ProductUsageDisplay(product.name, count, weeklyThreshold)
            },
            color = listOf(SecondaryContainer, SecondaryDark)
        )
    }
}

@Composable
private fun RoutineSection(
    title: String,
    @DrawableRes iconRes: Int,
    items: List<ProductUsageDisplay>,
    color: List<Color>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GradientIcon(iconRes, color)
        Text(
            text = title,
            fontFamily = mainFontFamily,
            fontWeight = FontWeight.Normal
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(items) { item ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.name,
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "Использовано раз: ${item.count}",
                    color = if (item.count > item.threshold) Primary else Error
                )
            }
        }
    }
}

@Composable
private fun GradientIcon(@DrawableRes id: Int, color: List<Color>) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier
            .size(30.dp)
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                val gradient = Brush.verticalGradient(colors = color)
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.SrcAtop)
                }
            }
    )
}

// Вспомогательный класс для доставки в UI
data class ProductUsageDisplay(
    val name: String,
    val count: Int,
    val threshold: Int
)