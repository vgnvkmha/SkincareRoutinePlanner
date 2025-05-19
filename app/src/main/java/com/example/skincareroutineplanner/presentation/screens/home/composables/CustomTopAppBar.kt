package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    streakCount: Int,
    streakCountBest: Int
) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                clip = false
            )
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        TopAppBar(
            colors = TopAppBarColors(
                containerColor = Surface,
                titleContentColor = OnSurface,
                actionIconContentColor = OnSurface,
                scrolledContainerColor = Background,
                navigationIconContentColor = OnPrimaryContainer
            ),
            title = { Text(text = "Skincare Routine Planner", modifier = Modifier.padding(15.dp), style = MaterialTheme.typography.titleLarge.copy(
                color = OnPrimaryContainer
            )) },
            //кнопка серии
            navigationIcon = {IconButton(enabled = true,
                onClick = {
                    showDialog = true
                }) {
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
                    text = streakCount.toString(),
                    color = Color.White,
                    fontSize = 5.sp,
                    modifier = Modifier
                        .offset(x = 8.dp, y = (-4).dp)
                        .background(
                            color = OnPrimaryContainer,
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

    if (showDialog) {
        StreakAlertDialog(
            showDialog = { showDialog = false},
            streakCount = streakCount,
            streakCountBest = streakCountBest
        )
    }
    
}