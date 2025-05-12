package com.example.skincareroutineplanner.presentation.screens.analytics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.skincareroutineplanner.presentation.screens.analytics.composables.RoutineAnalytic
import com.example.skincareroutineplanner.presentation.screens.analytics.composables.TimeRangeSelector
import java.sql.Time


class AnalyticFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column (modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    TimeRangeSelector()
                    RoutineAnalytic()
                }
            }
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        TODO("Добавить логику подсчёта статистики")
//    }
}