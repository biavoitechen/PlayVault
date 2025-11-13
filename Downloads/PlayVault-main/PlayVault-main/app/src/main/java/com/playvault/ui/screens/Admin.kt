package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.playvault.viewmodel.admin.AdminViewModel

@Composable
fun AdminScreen(
    onBack: () -> Unit,
    navController: NavHostController,
    viewModel: AdminViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Área Administrativa") },
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
            }

            state.lastMessage?.let {
                Text(text = it, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
            }

            Button(
                onClick = { viewModel.reduce(AdminEvent.RefreshCatalog) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Atualizar Catálogo")
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(state.games) { game ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(text = game.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = "ID: ${game.id}")
                            Text(text = "Destaque: ${if (game.isFeatured) "Sim" else "Não"}")
                        }
                    }
                }
            }
        }
    }
}
