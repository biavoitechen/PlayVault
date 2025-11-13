package com.playvault.ui.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme


@Composable
fun PlayVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) darkColorScheme(
        primary = AppColors.Primary,
        secondary = AppColors.Secondary,
        background = AppColors.BackgroundDark
    ) else lightColorScheme(
        primary = AppColors.Primary,
        secondary = AppColors.Secondary,
        background = AppColors.BackgroundLight
    )

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
