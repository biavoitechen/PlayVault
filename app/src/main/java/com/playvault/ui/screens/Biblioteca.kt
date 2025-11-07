package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class MeuJogo(val id: String, val titulo: String)

@Composable
fun BibliotecaScreen() {
    val jogos = rememberSaveable(saver = listSaver(
        save = { it.flatMap { j -> listOf(j.id, j.titulo) } },
        restore = { raw -> raw.chunked(2).map { (id, t) -> MeuJogo(id, t) }.toMutableStateList() }
    )) {
        mutableStateListOf(
            MeuJogo("sku-001", "Vault Runner"),
            MeuJogo("sku-004", "Chrono Rift"),
            MeuJogo("sku-005", "Skybound")
        )
    }
    ScreenScaffold(title = "Biblioteca") {
        if (jogos.isEmpty()) {
            Text("Sua biblioteca está vazia.", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Text("Compre algo na Loja para aparecer aqui.", style = MaterialTheme.typTypography.bodyMedium)
            return@ScreenScaffold
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(jogos, key = { it.id }) { jogo ->
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(jogo.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Button(onClick = { jogos.remove(jogo) }) { Text("Remover") }
                    }
                }
            }
        }
    }
}
