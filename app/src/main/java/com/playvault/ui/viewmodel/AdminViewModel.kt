package com.playvault.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.GameRepository
import com.playvault.domain.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(
    private val repo: GameRepository
): ViewModel() {

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    init {
        loadGames()
    }

    fun loadGames() {
        viewModelScope.launch {
            try {
                _games.value = repo.fetchGames()
            } catch (e: Exception) {
                _message.value = "Erro ao carregar jogos: ${e.message}"
            }
        }
    }

    fun createGame(game: Game) {
        viewModelScope.launch {
            repo.createGame(game)
            loadGames()
        }
    }

    fun updateGame(game: Game) {
        viewModelScope.launch {
            repo.updateGame(game)
            loadGames()
        }
    }

    fun deleteGame(id: String) {
        viewModelScope.launch {
            repo.deleteGame(id)
            loadGames()
        }
    }

    fun setFeatured(id: String, isFeatured: Boolean) {
        viewModelScope.launch {
            repo.setFeatured(id, isFeatured)
            loadGames()
        }
    }
}
