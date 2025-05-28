package com.example.skincareroutineplanner.presentation.screens.settings.composables.screen

data class SettingItem(
    val name: String,
    val lambda: () ->Unit
)
