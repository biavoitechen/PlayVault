// com.playvault.viewmodel.admin/AdminViewModel.kt
package com.playvault.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.AdminRepository
import com.playvault.contracts.*
import com.playvault.util.UiMapper.toGameUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AdminViewModel(
    private val repo: AdminRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AdminState())
    val state: StateFlow<AdminState> = _state.asStateFlow()

    init {
        loadRemoteCatalog()
    }

    private fun loadRemoteCatalog() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, lastMessage = null) }
            try {
                val domainGames = repo.fetchRemoteCatalog()
                val gamesUi = domainGames.map { it.toGameUi() }

                _state.update { it.copy(games = gamesUi, isLoading = false, lastMessage = "Catálogo Admin carregado.") }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, lastMessage = "Falha ao carregar Admin: ${e.message}")
                }
            }
        }
    }

    fun reduce(event: AdminEvent) {
        when (event) {
            is AdminEvent.CreateGameClicked -> handleCrudAction { repo.createGame(event.gameData) }
            is AdminEvent.EditGameClicked -> handleCrudAction { repo.updateGame(event.gameData) }
            is AdminEvent.DeleteGameClicked -> handleCrudAction { repo.deleteGame(event.gameId) }
            is AdminEvent.ToggleHighlight -> handleCrudAction { repo.setFeaturedGame(event.gameId, event.isFeatured) }
            AdminEvent.RefreshCatalog -> loadRemoteCatalog()
        }
    }

    private fun handleCrudAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, lastMessage = null) }
            try {
                action()
                _state.update { it.copy(lastMessage = "Ação concluída.", isLoading = false) }
                loadRemoteCatalog()
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, lastMessage = "Ação de Admin falhou: ${e.message}")
                }
            }
        }
    }
}