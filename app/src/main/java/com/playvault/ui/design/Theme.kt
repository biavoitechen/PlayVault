package com.playvault.ui.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PlayVaultTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = pvColorScheme(),
        typography = pvTypography(),
        shapes = pvShapes(),
        content = content
    )
}
