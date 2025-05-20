package com.example.skincareroutineplanner.presentation.screens.settings.composables

data class SettingItem(
    val name: String,
    val lambda: () ->Unit
)
