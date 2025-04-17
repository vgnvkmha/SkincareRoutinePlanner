package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.OnSecondary
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.PrimaryDarkEven


@Preview(showBackground = true)
@Composable
fun CustomSteakFragment() {
    Column(modifier = Modifier.fillMaxWidth().height(400.dp)) {
        Row(modifier = Modifier.fillMaxWidth().height(70.dp)) {
            IconButton(enabled = true,
                onClick = {}) {
                Icon(Icons.Default.Close, contentDescription = "To close fragment")
            }
            Text(text = "Streaks", color = OnSecondary, fontSize = 30.sp, textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.width(200.dp).height(200.dp)) {
                Icon(painter = painterResource(id = R.drawable.streak_icon_),
                    contentDescription = "Streak icon", modifier = Modifier.size(68.dp).graphicsLayer (alpha = 0.99f)
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Primary, OnSecondary, PrimaryDarkEven)
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        }.align(Alignment.TopCenter))
                Text(text = "0 day", textAlign = TextAlign.Center, color = OnSecondary, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
                Text(text = "Current streak", textAlign = TextAlign.Center, color = OnSecondary, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center).padding(top = 120.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
            }
            Box(modifier = Modifier.width(200.dp).height(200.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_medal),
                    contentDescription = "Streak icon", modifier = Modifier.size(68.dp).graphicsLayer (alpha = 0.99f)
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Yellow, Color.Red, PrimaryDarkEven)
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        }.align(Alignment.TopCenter))
                Text(text = "0 day", textAlign = TextAlign.Center, color = OnSecondary, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
                Text(text = "Longest streak", textAlign = TextAlign.Center, color = OnSecondary, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center).padding(top = 120.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp))

            }

        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Как это работает?", style = MaterialTheme.typography.titleLarge.copy(
                color = OnSecondary
            ),modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp)
            )
            Text(text = "Выполняй все свои задачи утром и вечером каждый день, чтобы твой streak рос",
                modifier = Modifier.padding(vertical = 20.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = OnSecondary
                ))

        }

    }


}