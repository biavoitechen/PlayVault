package com.playvault.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalheScreen(itemId: String, onBack: () -> Unit) {
    ScreenScaffold(title = "Detalhe") {
        Text("Detalhe do item: $itemId")
        Spacer(Modifier.height(12.dp))
        Text("Descrição mock do item $itemId.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Voltar") }
    }
}
