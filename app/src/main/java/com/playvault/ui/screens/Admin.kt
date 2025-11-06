package com.playvault.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AdminScreen(onClose: () -> Unit) {
    ScreenScaffold(title = "Área Admin") {
        Button(onClick = onClose) { Text("Fechar") }
    }
}
