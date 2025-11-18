package com.playvault.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.entity.GameEntity
import com.playvault.data.entity.LibraryEntryEntity
import com.playvault.data.repo.GameLibraryRepository
import com.playvault.data.repo.GameLibraryRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LibraryGameUi(
    val id: String,
    val title: String,
    val description: String,
    val installed: Boolean,
    val hoursPlayed: Int
)

data class LibraryUiState(
    val isLoading: Boolean = true,
    val games: List<LibraryGameUi> = emptyList()
)

class LibraryViewModel(
    private val repository: GameLibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState

    init {
        observeLibrary()
    }

    private fun observeLibrary() {
        viewModelScope.launch {
            combine(
                repository.observeStoreGames(),
                repository.observeLibraryEntries()
            ) { games, entries ->
                buildLibraryUi(games, entries)
            }.collect { items ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        games = items
                    )
                }
            }
        }
    }

    private fun buildLibraryUi(
        games: List<GameEntity>,
        entries: List<LibraryEntryEntity>
    ): List<LibraryGameUi> {
        val entryByGameId = entries.associateBy { it.gameId }

        return games.map { game ->
            val entry = entryByGameId[game.id]
            LibraryGameUi(
                id = game.id,
                title = game.title,
                description = game.description,
                installed = entry != null,
                hoursPlayed = entry?.hoursPlayed ?: 0
            )
        }
    }

    fun onInstallClick(gameId: String) {
        viewModelScope.launch {
            repository.installGame(gameId)
        }
    }

    fun onUninstallClick(gameId: String) {
        viewModelScope.launch {
            repository.uninstallGame(gameId)
        }
    }
}

@Composable
fun rememberLibraryViewModel(
    context: Context = LocalContext.current
): LibraryViewModel {
    val appContext = context.applicationContext
    return remember {
        val repo = GameLibraryRepositoryProvider.provide(appContext)
        LibraryViewModel(repo)
    }
}
