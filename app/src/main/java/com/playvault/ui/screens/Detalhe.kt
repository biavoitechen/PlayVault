package com.playvault.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetalheScreen(itemId: String) {
    ScreenScaffold(title = "Detalhe") {
        Text("Item: $itemId")
    }
}
