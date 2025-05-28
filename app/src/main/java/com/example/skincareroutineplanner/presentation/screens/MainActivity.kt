package com.example.skincareroutineplanner.presentation.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.skincareroutineplanner.presentation.screens.home.navgraphs.BottomAppBarNavigation


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomAppBarNavigation()
        }
    }
}