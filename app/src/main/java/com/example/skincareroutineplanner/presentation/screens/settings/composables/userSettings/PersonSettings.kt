package com.example.skincareroutineplanner.presentation.screens.settings.composables.userSettings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Outline
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun PersonSettings(
    onBackClick: () -> Unit,
    productViewModel: ProductViewModel,
    context:Context
) {
    val genderOptions = listOf("Мужчина", "Женщина")
    val ageOptions = listOf("менее 20", "20-40", "40+")
    val skinTypeOptions = listOf("Сухая", "Жирная", "Комбинированная", "Обычная")
    val climateOptions = listOf("Сухой", "Влажный")
    val sunExposureOptions = listOf("Редко", "Умеренно", "Часто")

    var selectedGender by remember { mutableStateOf<String?>(null) }
    var selectedAge by remember { mutableStateOf<String?>(null) }
    var selectedSkinType by remember { mutableStateOf<String?>(null) }
    var selectedClimate by remember { mutableStateOf<String?>(null) }
    var selectedSunExposure by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            color = Surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
                Text(
                    text = "Мои параметры",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = mainFontFamily,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        QuestionBlock(
            question = "Вы мужчина или женщина?",
            options = genderOptions,
            selectedOption = selectedGender,
            onOptionSelected = { selectedGender = it }
        )

        QuestionBlock(
            question = "Сколько вам лет?",
            options = ageOptions,
            selectedOption = selectedAge,
            onOptionSelected = { selectedAge = it }
        )

        QuestionBlock(
            question = "Какой у вас тип кожи?",
            options = skinTypeOptions,
            selectedOption = selectedSkinType,
            onOptionSelected = { selectedSkinType = it }
        )

        QuestionBlock(
            question = "Какой климат в вашем городе?",
            options = climateOptions,
            selectedOption = selectedClimate,
            onOptionSelected = { selectedClimate = it }
        )

        QuestionBlock(
            question = "Как часто вы находитесь на солнце?",
            options = sunExposureOptions,
            selectedOption = selectedSunExposure,
            onOptionSelected = { selectedSunExposure = it }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (selectedGender != null && selectedAge != null && selectedClimate != null && selectedSkinType != null && selectedSunExposure != null) {
                    productViewModel.updatePersonalInfo(
                        gender = selectedGender.toString(),
                        age = selectedAge.toString(),
                        climate = selectedClimate.toString(),
                        skinType = selectedSkinType.toString(),
                        sunExposure = selectedSunExposure.toString()
                    )
                    onBackClick()
                }
                else {
                    Toast.makeText(context, "Выберите все варианты!", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonColors(
                containerColor = PrimaryContainer,
                contentColor = OnPrimaryContainer,
                disabledContentColor = Outline,
                disabledContainerColor = Outline
            )
        ) {
            Text("Подтвердить изменения")
        }
    }
}