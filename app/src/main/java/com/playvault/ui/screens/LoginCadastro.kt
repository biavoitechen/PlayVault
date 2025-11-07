package com.playvault.ui.screens

<<<<<<< HEAD
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.playvault.contracts.AuthEvent
import com.playvault.ui.viewmodel.AuthViewModel

@Composable
fun LoginCadastroScreen(vm: AuthViewModel, onAuthOk: () -> Unit) {
    val st by vm.state.collectAsState()
    var isLoginMode by remember { mutableStateOf(true) }
    LaunchedEffect(st.isLogged) { if (st.isLogged) onAuthOk() }
    ScreenScaffold(title = if (isLoginMode) "Login" else "Criar Conta") {
        if (st.isLoading) { CircularProgressIndicator(); return@ScreenScaffold }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(), value = st.email,
            onValueChange = { vm.reduce(AuthEvent.EmailChanged(it)) },
            label = { Text("Email") }, singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(), value = st.password,
            onValueChange = { vm.reduce(AuthEvent.PasswordChanged(it)) },
            label = { Text("Senha (mín. 6)") }, singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            if (isLoginMode) vm.reduce(AuthEvent.SubmitLogin) else vm.reduce(AuthEvent.SubmitRegister)
        }) { Text(if (isLoginMode) "Entrar" else "Cadastrar") }
        Spacer(Modifier.height(8.dp))
        TextButton(modifier = Modifier.fillMaxWidth(), onClick = { isLoginMode = !isLoginMode }) {
            Text(if (isLoginMode) "Ainda não tem conta? Cadastre-se" else "Já tem conta? Fazer login")
        }
        st.lastMessage?.let { Spacer(Modifier.height(12.dp)); Text(it, color = MaterialTheme.colorScheme.secondary) }
=======
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
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
    }
}
