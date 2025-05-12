package com.example.skincareroutineplanner.presentation.screens.analytics.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnSecondaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSecondaryDark
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Outline
import com.example.skincareroutineplanner.ui.theme.Secondary
import com.example.skincareroutineplanner.ui.theme.SecondaryContainer
import com.example.skincareroutineplanner.ui.theme.SecondaryContainerDark
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Preview(showBackground = true)
@Composable
fun TimeRangeSelector() {
    val options = listOf("7 дней", "2 недели", "месяц")
    var selectedOption = remember { mutableStateOf("7 days") }

    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Background)
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption.value
            Text(
                text = option,
                fontFamily = mainFontFamily,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) SecondaryContainer else Surface)
                    .clickable { selectedOption.value = option }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = if (isSelected) OnSecondaryContainer else OnSurface,
                fontWeight = FontWeight.Normal
            )
        }
    }
}