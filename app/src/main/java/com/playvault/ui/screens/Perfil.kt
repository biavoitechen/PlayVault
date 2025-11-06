package com.playvault.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PerfilScreen(onClickLogin: () -> Unit, onClickAdmin: () -> Unit) {
    ScreenScaffold(title = "Perfil") {
        Button(onClick = onClickLogin) { Text("Login") }
        Button(onClick = onClickAdmin) { Text("Admin") }
    }
}
