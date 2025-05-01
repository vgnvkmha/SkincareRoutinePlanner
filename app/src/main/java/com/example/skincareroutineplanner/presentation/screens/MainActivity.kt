package com.example.skincareroutineplanner.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.skincareroutineplanner.presentation.screens.home.composables.ScheduleList
import com.example.skincareroutineplanner.ui.theme.SkincareRoutinePlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkincareRoutinePlannerTheme {
                ScheduleList()
            }
        }

    }
}
