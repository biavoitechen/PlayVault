package com.playvault.ui.viewmodel

<<<<<<< HEAD
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
=======
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playvault.data.db.AppDatabase
import com.playvault.data.repo.AuthRepository
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    var lastMessage by mutableStateOf("")

    fun login(email: String, pass: String, cb: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val ok = repo.login(email, pass)
            lastMessage = if (ok) "" else "Credenciais inválidas"
            cb(ok)
        }
    }

    fun register(name: String, email: String, pass: String, cb: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val res = repo.register(name, email, pass)
            lastMessage = res.exceptionOrNull()?.message ?: "Cadastro OK"
            cb(res.isSuccess)
        }
    }
}

class AuthVMFactory(private val repo: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// Serviço simples pra prover Repo/VM sem DI pesado
object ServiceLocator {
    private var repo: AuthRepository? = null
    fun provideRepo(app: android.app.Application): AuthRepository {
        val current = repo
        if (current != null) return current
        val db = AppDatabase.get(app)
        return AuthRepository(db.userDao()).also { repo = it }
    }
}

@Composable
fun rememberAuthViewModel(): AuthViewModel {
    val ctx = LocalContext.current.applicationContext as android.app.Application
    val factory = AuthVMFactory(ServiceLocator.provideRepo(ctx))
    return viewModel(factory = factory)
}
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
