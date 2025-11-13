package com.playvault.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class LojaItem(val id: String, val titulo: String, val preco: String)

@Composable
fun LojaScreen(onClickDetalhe: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val base = remember {
        listOf(
            LojaItem("sku-001", "Vault Runner", "R$ 29,90"),
            LojaItem("sku-002", "Crystal Shards", "R$ 19,90"),
            LojaItem("sku-003", "Neon Streets", "R$ 39,90"),
            LojaItem("sku-004", "Chrono Rift", "R$ 24,90"),
            LojaItem("sku-005", "Skybound", "R$ 12,90"),
        )
    }
    val filtrado = remember(query, base) {
        if (query.isBlank()) base else base.filter {
            it.titulo.contains(
                query,
                true
            ) || it.id.contains(query, true)
        }
    }
    ScreenScaffold(title = "Loja") {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query, onValueChange = { query = it },
            label = { Text("Pesquisar na loja") }, singleLine = true
        )
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(filtrado, key = { it.id }) { game ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onClickDetalhe(game.id) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(
                            game.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(game.preco, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(4.dp))
                        Text("ID: ${game.id}", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}
