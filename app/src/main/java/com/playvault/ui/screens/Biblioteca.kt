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
fun BibliotecaScreen() {
    val jogos = listOf("Jogo 1", "Jogo 2", "Jogo 3")

    ScreenScaffold(title = "Biblioteca") { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(jogos) { jogo ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(jogo, style = MaterialTheme.typography.titleMedium)
                        Text("Horas jogadas: 0h", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
