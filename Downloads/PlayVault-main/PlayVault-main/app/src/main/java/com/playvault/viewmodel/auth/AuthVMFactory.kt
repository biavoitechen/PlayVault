package com.playvault.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playvault.data.repo.AuthRepository
import com.playvault.viewmodel.auth.AuthViewModel

class AuthViewModelFactory(private val repo: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun rememberAuthViewModel(repo: AuthRepository): AuthViewModel {
    return viewModel(factory = AuthViewModelFactory(repo))
}
