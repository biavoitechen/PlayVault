package com.playvault.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playvault.ui.components.ScreenScaffold
import com.playvault.ui.viewmodel.AuthViewModel

@Composable
fun PerfilScreen(
    vm: AuthViewModel,
    onLogout: () -> Unit
) {
    val state = vm.state.collectAsState().value

    ScreenScaffold(
        title = "Perfil"
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Meu perfil",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Estado atual: ${state}",
                style = MaterialTheme.typography.bodySmall
            )

            Button(onClick = onLogout) {
                Text("Sair da conta")
            }
        }
    }
}
