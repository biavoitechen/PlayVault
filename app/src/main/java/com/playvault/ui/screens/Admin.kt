package com.playvault.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Admin") {
        Text("Área Administrativa (mock)")
        Spacer(Modifier.height(8.dp))
        Text("- Futuro: gestão de usuários, métricas, etc.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Voltar") }
    }
}
