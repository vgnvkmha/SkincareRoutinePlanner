package com.example.skincareroutineplanner.presentation.screens.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.PrimaryContainerDark
import com.example.skincareroutineplanner.ui.theme.mainFontFamily


@Composable
fun AboutAlertDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = Background,
        onDismissRequest = onDismiss,
        title = {
            Text(text = "О приложении", style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Column {
                Text(
                    "Это приложение создаёт рутину, исходя из твоих средств и параметров.\n" +
                            "Чтобы добавить параметры, нажми на кнопку \"Мои данные\" и пройди короткий вопрос.\n" +
                            "Далее добавь все свои средства, при помощи поиска\n" +
                            "После этого нажми на кнопку \"Сгенирировать расписание\" и" +
                            " пользуйся всем доступным функционалом!",
                    modifier = Modifier.padding(8.dp),
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonColors(
                    containerColor = PrimaryContainerDark,
                    contentColor = OnPrimary,
                    disabledContentColor = Primary,
                    disabledContainerColor = Primary
                )
            ) {
                Text("Закрыть")
            }
        }
    )

}