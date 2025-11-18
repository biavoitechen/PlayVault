package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playvault.ui.components.ScreenScaffold

@Composable
fun AdminScreen(
    onBack: () -> Unit
) {
    ScreenScaffold(
        title = "Administração",
        onBack = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Painel do Administrador", style = MaterialTheme.typography.headlineSmall)
            Text("Aqui você pode gerenciar jogos, usuários e outras configurações.")
            // TODO: ligar com o restante do CRUD/Room conforme o trabalho.
        }
    }
}
