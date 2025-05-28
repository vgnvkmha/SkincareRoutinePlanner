package com.example.skincareroutineplanner.presentation.screens.settings.composables.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnBackground
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun SettingsList(
    lambdaProducts: () -> Unit,
    lambdaSettings: () -> Unit,
    lambdaAbout: () -> Unit,
    lambdaLogOut: () -> Unit,
    lambdaCreateSchedule: () -> Unit
) {
    val settingsItems = listOf(
        SettingItem("Мои средства", lambdaProducts),
        SettingItem("Мои данные", lambdaSettings),
        SettingItem("О приложении", lambdaAbout),
        SettingItem("Создать расписание", lambdaCreateSchedule),
        SettingItem("Выход", lambdaLogOut)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(810.dp)
            .background(Background)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Мои параметры",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mainFontFamily,
            color = OnBackground,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Divider()

        settingsItems.forEach { item ->
            SettingRow(title = item.name, onClick = item.lambda)
            Divider()
        }
    }
}

@Composable
fun SettingRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = mainFontFamily,
            color = Color.Black
        )
    }
}