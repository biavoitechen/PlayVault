package com.playvault.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.playvault.data.repo.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoginMode: Boolean = true,
    val isLogged: Boolean = false,
    val isLoading: Boolean = false,
    val lastMessage: String? = null,
)

class AuthViewModel(
    private val authRepository: AuthRepository? = null
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state

    fun onNameChange(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun toggleMode() {
        _state.update { it.copy(isLoginMode = !it.isLoginMode, lastMessage = null) }
    }

    fun submit() {
        val current = _state.value

        if (current.email.isBlank() || current.password.isBlank()) {
            _state.update { it.copy(lastMessage = "Preencha e-mail e senha.") }
            return
        }

        if (current.isLoginMode) {
            // Login fake
            _state.update {
                it.copy(
                    isLogged = true,
                    lastMessage = "Login realizado com sucesso."
                )
            }
        } else {
            if (current.name.isBlank()) {
                _state.update { it.copy(lastMessage = "Preencha o nome para cadastro.") }
                return
            }
            _state.update {
                it.copy(
                    isLogged = true,
                    lastMessage = "Cadastro realizado com sucesso."
                )
            }
        }
    }

    fun clearMessage() {
        _state.update { it.copy(lastMessage = null) }
    }

    fun logout() {
        _state.update {
            AuthUiState(
                isLoginMode = true,
                isLogged = false
            )
        }
    }
}
