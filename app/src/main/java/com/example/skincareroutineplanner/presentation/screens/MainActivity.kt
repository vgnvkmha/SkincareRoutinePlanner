package com.example.skincareroutineplanner.presentation.screens

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.skincareroutineplanner.presentation.screens.analytics.fragments.AnalyticFragment
import com.example.skincareroutineplanner.presentation.screens.home.composables.ScheduleList
import com.example.skincareroutineplanner.ui.theme.SkincareRoutinePlannerTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FragmentContainerWithButton()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FragmentContainerWithButton() {
    val context = LocalContext.current
    val activity = context as AppCompatActivity
    val fragmentManager = activity.supportFragmentManager
    var containerId by remember { mutableStateOf(View.NO_ID) }

    SkincareRoutinePlannerTheme {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            // Контейнер для фрагмента
            AndroidView(
                factory = { ctx ->
                    FrameLayout(ctx).apply {
                        id = View.generateViewId()
                        containerId = id
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                modifier = Modifier
                    .weight(1f) // фрагмент занимает всё оставшееся место
                    .fillMaxWidth()
            )

            // Кнопка
            Button(
                onClick = {
                    val analyticFragment = AnalyticFragment()
                    fragmentManager.beginTransaction()
                        .replace(containerId, analyticFragment)
                        .commit()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Показать фрагмент")
            }
        }
    }
}