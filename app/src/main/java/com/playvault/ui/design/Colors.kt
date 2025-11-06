package com.playvault.ui.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

private val Purple = Color(0xFF7C4DFF)
private val Teal = Color(0xFF00BFA5)
private val Bg = Color(0xFF121212)
private val Surface = Color(0xFF1C1C1C)

fun pvColorScheme(): ColorScheme = darkColorScheme(
    primary = Purple,
    onPrimary = Color.White,
    secondary = Teal,
    onSecondary = Color.Black,
    background = Bg,
    onBackground = Color(0xFFEDEDED),
    surface = Surface,
    onSurface = Color(0xFFEDEDED)
)
