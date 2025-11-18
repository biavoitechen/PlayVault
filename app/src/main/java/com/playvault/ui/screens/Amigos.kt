package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playvault.ui.components.ScreenScaffold

@Composable
fun AmigosScreen() {
    val amigos = listOf("Ana", "Rapha", "Mell")

    ScreenScaffold(title = "Amigos") { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(amigos) { amigo ->
                ListItem(
                    headlineContent = { Text(amigo) },
                    supportingContent = { Text("Clique para ver detalhes em versões futuras") }
                )
                Divider()
            }
        }
    }
}
