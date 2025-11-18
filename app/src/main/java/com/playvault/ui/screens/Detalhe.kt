package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playvault.ui.components.ScreenScaffold

@Composable
fun DetalheScreen(
    itemId: String,
    onBack: () -> Unit
) {
    ScreenScaffold(
        title = "Detalhes do jogo",
        onBack = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Detalhes do item $itemId", style = MaterialTheme.typography.headlineSmall)
            Text("Aqui vão as informações completas do jogo selecionado.")
        }
    }
}
