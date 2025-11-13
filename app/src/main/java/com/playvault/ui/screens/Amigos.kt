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
import androidx.navigation.NavHostController

private data class Amigo(val id: String, val nome: String)

@Composable
fun AmigosScreen(navController: NavHostController) {
    val snackbar = remember { SnackbarHostState() }
    var query by remember { mutableStateOf("") }
    val base = remember {
        listOf(
            Amigo("u-001", "Aline Costa"), Amigo("u-002", "Bruno Martins"),
            Amigo("u-003", "Carla Nogueira"), Amigo("u-004", "Diego Lima"),
            Amigo("u-005", "Eduarda Faria"),
        )
    }
    val filtrado = remember(query, base) {
        if (query.isBlank()) base else base.filter {
            it.nome.contains(
                query,
                true
            ) || it.id.contains(query, true)
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbar) }) { inner ->
        Column(Modifier.padding(inner)) {
            ScreenScaffold(title = "Amigos") {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar amigo") },
                    singleLine = true
                )
                Spacer(Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filtrado, key = { it.id }) { amigo ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable {
                                LaunchedEffect(amigo.id) { snackbar.showSnackbar("Abrindo perfil de ${amigo.nome}…") }
                            },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(Modifier.padding(14.dp)) {
                                Text(
                                    amigo.nome,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(Modifier.height(4.dp))
                                Text("ID: ${amigo.id}", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }
        }
    }
}
