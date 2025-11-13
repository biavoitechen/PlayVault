// com.playvault.viewmodel.library/LibraryViewModel.kt
package com.playvault.viewmodel.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.LibraryRepository
import com.playvault.contracts.*
import com.playvault.util.UiMapper.toLibraryGameUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val repo: LibraryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LibraryState())
    val state: StateFlow<LibraryState> = _state.asStateFlow()

    init {
        repo.getUserLibraryStream()
            .map { domainEntries -> domainEntries.map { it.toLibraryGameUi() } }
            .onEach { libraryUi ->
                _state.update { it.copy(libraryGames = libraryUi, isLoading = false, lastMessage = null) }
            }
            .onStart { _state.update { it.copy(isLoading = true) } }
            .catch { error ->
                _state.update {
                    it.copy(isLoading = false, lastMessage = "Falha na Biblioteca: ${error.message}")
                }
            }
            .launchIn(viewModelScope)
    }

    fun reduce(event: LibraryEvent) {
        when (event) {
            is LibraryEvent.InstallGame -> handleInstallation(event.gameId, true)
            is LibraryEvent.UninstallGame -> handleInstallation(event.gameId, false)
            is LibraryEvent.UpdateHoursPlayed -> handleUpdateHours(event.gameId, event.newMinutes)
            LibraryEvent.RefreshLibrary -> {}
        }
    }

    private fun handleInstallation(gameId: String, install: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                if (install) repo.installGame(gameId) else repo.uninstallGame(gameId)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, lastMessage = "Erro na ação: ${e.message}") }
            }
        }
    }

    private fun handleUpdateHours(gameId: String, newMinutes: Long) {
        viewModelScope.launch {
            try {
                repo.updateHoursPlayed(gameId, newMinutes)
            } catch (e: Exception) {
                _state.update { it.copy(lastMessage = "Erro ao registrar horas: ${e.message}") }
            }
        }
    }
}