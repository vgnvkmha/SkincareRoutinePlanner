package com.example.skincareroutineplanner.presentation.screens.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreakAlertDialog(
    showDialog: () -> Unit,
    streakCount: Int,
    streakCountBest: Int
) {
    BasicAlertDialog(
        onDismissRequest = showDialog
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            color = com.example.skincareroutineplanner.ui.theme.Surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                CustomSteakFragment(
                    showDialog,
                    streakCount,
                    streakCountBest
                )
            }
        }
    }
}