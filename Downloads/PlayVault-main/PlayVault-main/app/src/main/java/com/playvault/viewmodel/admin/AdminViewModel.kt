// com.playvault.viewmodel.admin/AdminViewModel.kt
package com.playvault.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.AdminRepository
import com.playvault.data.repo.GameRepository
import com.playvault.data.model.GameUi
import com.playvault.contracts.*
import com.playvault.util.UiMapper.toGameUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AdminViewModel(
    private val adminRepo: AdminRepository,
    private val gameRepo: GameRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AdminState())
    val state: StateFlow<AdminState> = _state.asStateFlow()

    // Flow de jogos sincronizados (Firestore + Room)
    val games = gameRepo.getGames()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Carrega catálogo remoto e sincroniza
        loadRemoteCatalog()
        sync()
    }

    private fun loadRemoteCatalog() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, lastMessage = null) }
            try {
                val domainGames = adminRepo.fetchRemoteCatalog()
                val gamesUi = domainGames.map { it.toGameUi() }

                _state.update {
                    it.copy(games = gamesUi, isLoading = false, lastMessage = "Catálogo Admin carregado.")
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, lastMessage = "Falha ao carregar Admin: ${e.message}")
                }
            }
        }
    }

    private fun handleCrudAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, lastMessage = null) }
            try {
                action()
                _state.update { it.copy(lastMessage = "Ação concluída.", isLoading = false) }
                sync()
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, lastMessage = "Ação de Admin falhou: ${e.message}")
                }
            }
        }
    }

    // CRUDs integrados com o Firestore + Room via GameRepository
    fun addGame(game: Game) = handleCrudAction {
        gameRepo.addGame(game)
        gameRepo.syncGames()
    }

    fun updateGame(game: Game) = handleCrudAction {
        gameRepo.updateGame(game)
        gameRepo.syncGames()
    }

    fun deleteGame(id: String) = handleCrudAction {
        gameRepo.deleteGame(id)
        gameRepo.syncGames()
    }

    fun sync() = viewModelScope.launch {
        gameRepo.syncGames()
    }

    fun reduce(event: AdminEvent) {
        when (event) {
            is AdminEvent.CreateGameClicked -> addGame(event.gameData)
            is AdminEvent.EditGameClicked -> updateGame(event.gameData)
            is AdminEvent.DeleteGameClicked -> deleteGame(event.gameId)
            is AdminEvent.ToggleHighlight -> handleCrudAction {
                adminRepo.setFeaturedGame(event.gameId, event.isFeatured)
            }
            AdminEvent.RefreshCatalog -> sync()
        }
    }
}
