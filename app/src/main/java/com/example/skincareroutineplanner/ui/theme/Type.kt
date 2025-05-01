package com.example.skincareroutineplanner.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.R

val mainFontFamily = FontFamily(
    Font(R.font.opensans_variable_font_wdth_wght, FontWeight.Normal),
    Font(R.font.opensans_italic_variable_font_wdth_wght, FontWeight.Black)
)
// Set of Material typography styles to start with
private val typography = Typography(
    headlineSmall = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)