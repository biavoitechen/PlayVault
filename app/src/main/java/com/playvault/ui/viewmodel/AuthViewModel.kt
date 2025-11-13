package com.playvault.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.contracts.AuthEvent
import com.playvault.contracts.AuthState
import com.playvault.data.repo.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository): ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun reduce(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> _state.value = _state.value.copy(email = event.value, lastMessage = null)
            is AuthEvent.PasswordChanged -> _state.value = _state.value.copy(password = event.value, lastMessage = null)
            AuthEvent.SubmitLogin -> login()
            AuthEvent.SubmitRegister -> register()
            AuthEvent.Logout -> _state.value = _state.value.copy(isLogged = false, lastMessage = "Sessão encerrada")
        }
    }
    private fun login() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true, lastMessage = null)
        val ok = repo.login(_state.value.email.trim(), _state.value.password)
        _state.value = if (ok) _state.value.copy(isLoading=false,isLogged=true,lastMessage="Login ok")
                       else _state.value.copy(isLoading=false,isLogged=false,lastMessage="Credenciais inválidas")
    }
    private fun register() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true, lastMessage = null)
        val ok = repo.register(_state.value.email.trim(), _state.value.password)
        _state.value = if (ok) _state.value.copy(isLoading=false,isLogged=true,lastMessage="Cadastro efetuado")
                       else _state.value.copy(isLoading=false,isLogged=false,lastMessage="Email já existe ou dados inválidos")
    }
}
