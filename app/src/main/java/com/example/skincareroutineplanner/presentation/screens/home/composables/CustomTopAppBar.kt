package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.*

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = PrimaryContainer,
            titleContentColor = OnPrimaryContainer,
            actionIconContentColor = OnPrimaryContainer,
            scrolledContainerColor = Background,
            navigationIconContentColor = OnPrimaryContainer
        ),
        title = { Text(text = "Skincare Routine Planner", modifier = Modifier.padding(15.dp), style = MaterialTheme.typography.titleLarge.copy(
            color = OnPrimaryContainer
        )) },
        navigationIcon = {IconButton(enabled = true,
            onClick = { TODO("Добавить развертвывание информации о streak (что-то типа fragment)") }) {
            Icon(painter = painterResource(id = R.drawable.streak_icon_),
                contentDescription = "streak icon",
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer (alpha = 0.99f)
                    .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(SecondaryDark, Secondary, PrimaryDark, Primary)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.SrcAtop)
                    }
                },
                tint = Color.Unspecified //убираем стандартную заливку иконки
            )
            Text(
                text = "0",
                color = Color.White,
                fontSize = 5.sp,
                modifier = Modifier
                    .offset(x = 8.dp, y = (-4).dp)
                    .background(
                        color = OnSecondary,
                        shape = CircleShape
                    )
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        } },
        actions = {

            IconButton(enabled = true,
                onClick = {}) {
                Icon(Icons.Default.DateRange, contentDescription = "calendar icon")
            }
        }
    )
}