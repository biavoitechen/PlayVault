package com.playvault.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun LojaScreen(onClickDetalhe: (String) -> Unit) {
    var q by remember { mutableStateOf("") }
    ScreenScaffold(title = "Loja") {
        OutlinedTextField(
            value = q,
            onValueChange = { q = it },
            label = { Text("Pesquisar") }
        )
        Button(onClick = { onClickDetalhe("sku-123") }) {
            Text("Abrir Detalhe (sku-123)")
        }
    }
}
