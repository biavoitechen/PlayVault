package com.playvault.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.playvault.ui.viewmodel.AuthViewModel
import com.playvault.ui.viewmodel.rememberAuthViewModel

@Composable
fun LoginScreen(onLoginOk: () -> Unit, onClickCadastro: () -> Unit) {
    val vm: AuthViewModel = rememberAuthViewModel()
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    ScreenScaffold(title = "Login") {
        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(pass, { pass = it }, label = { Text("Senha") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            vm.login(email, pass) { success -> if (success) onLoginOk() }
        }) { Text("Entrar") }
        TextButton(onClick = onClickCadastro) { Text("Criar conta") }
        if (vm.lastMessage.isNotEmpty()) Text(vm.lastMessage)
    }
}

@Composable
fun CadastroScreen(onCadastroOk: () -> Unit) {
    val vm: AuthViewModel = rememberAuthViewModel()
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    ScreenScaffold(title = "Cadastro") {
        OutlinedTextField(nome, { nome = it }, label = { Text("Nome") })
        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(pass, { pass = it }, label = { Text("Senha") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            vm.register(nome, email, pass) { ok -> if (ok) onCadastroOk() }
        }) { Text("Criar") }
        if (vm.lastMessage.isNotEmpty()) Text(vm.lastMessage)
    }
}
