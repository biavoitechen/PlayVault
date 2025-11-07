package com.playvault.ui.screens

<<<<<<< HEAD
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Admin") {
        Text("Área Administrativa (mock)")
        Spacer(Modifier.height(8.dp))
        Text("- Futuro: gestão de usuários, métricas, etc.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Voltar") }
=======
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AdminScreen(onClose: () -> Unit) {
    ScreenScaffold(title = "Área Admin") {
        Button(onClick = onClose) { Text("Fechar") }
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
    }
}
