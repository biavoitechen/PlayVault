package com.playvault.contracts

sealed interface AuthEvent {
    data class EmailChanged(val value: String): AuthEvent
    data class PasswordChanged(val value: String): AuthEvent
    data object SubmitLogin: AuthEvent
    data object SubmitRegister: AuthEvent
    data object Logout: AuthEvent
}

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val lastMessage: String? = null
)
