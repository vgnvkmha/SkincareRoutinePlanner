package com.example.skincareroutineplanner.presentation.screens.settings.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.ui.theme.OnBackground
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Outline
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryDark
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun QuestionBlock(
    question: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(
            text = question,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            color = OnBackground,
            fontFamily = mainFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))
        options.forEach { option ->
            val isSelected = option == selectedOption
            OutlinedButton(
                onClick = { onOptionSelected(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) PrimaryContainer else Color.Transparent
                ),
                border = BorderStroke(1.dp, if (isSelected) PrimaryDark else Outline)
            ) {
                Text(
                    text = option,
                    color = if (isSelected) OnPrimaryContainer else OnBackground
                )
            }
        }
    }
}