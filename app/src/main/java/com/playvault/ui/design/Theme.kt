package com.playvault.ui.design

<<<<<<< HEAD
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun PlayVaultTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
=======
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PlayVaultTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = pvColorScheme(),
        typography = pvTypography(),
        shapes = pvShapes(),
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
        content = content
    )
}
