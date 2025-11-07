package com.playvault.ui.screens

<<<<<<< HEAD
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playvault.contracts.AuthEvent
import com.playvault.ui.viewmodel.AuthViewModel

@Composable
fun PerfilScreen(vm: AuthViewModel, onGotoLogin: () -> Unit, onGotoAdmin: () -> Unit) {
    val state by vm.state.collectAsState()
    ScreenScaffold(title = "Perfil") {
        if (state.isLoading) { CircularProgressIndicator(); return@ScreenScaffold }
        if (state.isLogged) {
            Text("Sessão ativa"); Spacer(Modifier.height(8.dp))
            state.lastMessage?.let { Text(it) }; Spacer(Modifier.height(16.dp))
            Column {
                Button(onClick = { vm.reduce(AuthEvent.Logout) }) { Text("Sair") }
                Spacer(Modifier.height(8.dp))
                Button(onClick = onGotoAdmin) { Text("Área Administrativa") }
            }
        } else {
            Text("Você não está logado."); Spacer(Modifier.height(8.dp))
            state.lastMessage?.let { Text(it) }; Spacer(Modifier.height(16.dp))
            Button(onClick = onGotoLogin) { Text("Fazer login / cadastro") }
        }
=======
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PerfilScreen(onClickLogin: () -> Unit, onClickAdmin: () -> Unit) {
    ScreenScaffold(title = "Perfil") {
        Button(onClick = onClickLogin) { Text("Login") }
        Button(onClick = onClickAdmin) { Text("Admin") }
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
    }
}
