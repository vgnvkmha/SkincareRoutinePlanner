package com.example.skincareroutineplanner.presentation.screens.home.composables

import android.graphics.Paint.Align
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.OnBackground
import com.example.skincareroutineplanner.ui.theme.OnPrimary
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnPrimaryDark
import com.example.skincareroutineplanner.ui.theme.OnSecondaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.OnSurfaceDark
import com.example.skincareroutineplanner.ui.theme.Primary
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryContainerDark
import com.example.skincareroutineplanner.ui.theme.PrimaryDark
import com.example.skincareroutineplanner.ui.theme.Secondary
import com.example.skincareroutineplanner.ui.theme.SecondaryDark
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.SurfaceDark
import com.example.skincareroutineplanner.ui.theme.mainFontFamily


@Preview(showBackground = true)
@Composable
fun CustomSteakFragment() {
    Column(modifier = Modifier.fillMaxWidth().height(400.dp)) {
        Row(modifier = Modifier.fillMaxWidth().height(70.dp).background(Surface)) {
            IconButton(enabled = true,
                onClick = {}) {
                Icon(Icons.Default.Close, contentDescription = "To close fragment")
            }
            Text(text = "Streaks", color = OnSurface, fontSize = 30.sp, textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = PrimaryContainer, blurRadius = 1f, offset = Offset(5.0f, 10.0f)
                    )
                ),
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.width(200.dp).height(200.dp).background(Surface)) {
                Icon(painter = painterResource(id = R.drawable.streak_icon_),
                    contentDescription = "Streak icon", modifier = Modifier.size(68.dp).graphicsLayer (alpha = 0.99f)
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(SecondaryDark, Secondary, PrimaryDark, Primary)
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        }.align(Alignment.TopCenter))
                Text(text = "0 day", textAlign = TextAlign.Center, fontFamily = mainFontFamily, fontWeight = FontWeight.Black, color = OnSurface, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
                Text(text = "Current streak", fontFamily = mainFontFamily, fontWeight = FontWeight.Black, textAlign = TextAlign.Center, color = OnSurface, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center).padding(top = 120.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
            }
            Box(modifier = Modifier.width(200.dp).height(200.dp).background(Surface)) {
                Icon(painter = painterResource(id = R.drawable.ic_medal),
                    contentDescription = "Streak icon", modifier = Modifier.size(68.dp).graphicsLayer (alpha = 0.99f)
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Yellow, Color.Red)
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        }.align(Alignment.TopCenter))
                Text(text = "0 day", textAlign = TextAlign.Center, fontFamily = mainFontFamily, fontWeight = FontWeight.Black, color = OnSurface, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 4.dp, vertical = 2.dp))
                Text(text = "Longest streak", textAlign = TextAlign.Center, fontFamily = mainFontFamily, fontWeight = FontWeight.Black, color = OnSurface, fontSize = 25.sp, modifier = Modifier
                    .align(Alignment.Center).padding(top = 120.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp))

            }

        }
        Column(modifier = Modifier.fillMaxSize().background(Background).padding(16.dp)) {
            Text(textAlign = TextAlign.Center, text = "Как это работает?", fontFamily = mainFontFamily, fontWeight = FontWeight.Black, style = MaterialTheme.typography.titleLarge.copy(
                color = OnBackground
            ),modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Выполняй все свои задачи утром и вечером каждый день, чтобы твой streak рос",
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = OnBackground
                ))

        }

    }


}