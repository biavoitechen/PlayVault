package com.playvault.ui.design

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF6750A4)
val md_theme_light_secondary = Color(0xFF625B71)
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_surface = Color(0xFFFFFBFE)
val md_theme_light_onPrimary = Color.White

val md_theme_dark_primary = Color(0xFFD0BCFF)
val md_theme_dark_secondary = Color(0xFFCCC2DC)
val md_theme_dark_background = Color(0xFF1C1B1F)
val md_theme_dark_surface = Color(0xFF1C1B1F)
val md_theme_dark_onPrimary = Color(0xFF381E72)

val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    onPrimary = md_theme_light_onPrimary
)

val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    onPrimary = md_theme_dark_onPrimary
)
