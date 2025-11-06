package com.playvault.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BibliotecaScreen() {
    ScreenScaffold(title = "Biblioteca") {
        Button(onClick = { /* TODO: abrir item */ }) { Text("Item mais recente") }
    }
}
