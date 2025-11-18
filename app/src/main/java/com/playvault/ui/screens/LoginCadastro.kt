package com.playvault.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.playvault.ui.components.ScreenScaffold
import com.playvault.ui.viewmodel.AuthViewModel

@Composable
fun LoginCadastroScreen(
    vm: AuthViewModel,
    onGotoAdmin: () -> Unit,
    onAuthOk: () -> Unit
) {
    val context = LocalContext.current
    val state by vm.state.collectAsState()

    LaunchedEffect(state.isLogged) {
        if (state.isLogged) {
            onAuthOk()
        }
    }

    LaunchedEffect(state.lastMessage) {
        val msg = state.lastMessage
        if (!msg.isNullOrBlank()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            vm.clearMessage()
        }
    }

    ScreenScaffold(title = if (state.isLoginMode) "Login" else "Cadastro") { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!state.isLoginMode) {
                    OutlinedTextField(
                        value = state.name,
                        onValueChange = vm::onNameChange,
                        label = { Text("Nome") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OutlinedTextField(
                    value = state.email,
                    onValueChange = vm::onEmailChange,
                    label = { Text("E-mail") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = vm::onPasswordChange,
                    label = { Text("Senha") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { vm.submit() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading
                ) {
                    Text(if (state.isLoginMode) "Entrar" else "Cadastrar")
                }

                TextButton(onClick = { vm.toggleMode() }) {
                    Text(
                        if (state.isLoginMode)
                            "Não tem conta? Cadastre-se"
                        else
                            "Já tem conta? Fazer login"
                    )
                }

                TextButton(onClick = onGotoAdmin) {
                    Text("Área do administrador")
                }
            }
        }
    }
}
