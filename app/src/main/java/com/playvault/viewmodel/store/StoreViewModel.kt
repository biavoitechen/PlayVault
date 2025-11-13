// com.playvault.viewmodel.store/StoreViewModel.kt
package com.playvault.viewmodel.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.StoreRepository // Adaptado para o padrão do colega
import com.playvault.contracts.* // Contratos de Estado, Evento, etc.
import com.playvault.util.UiMapper.toGameUi
import kotlinx.coroutines.flow.*

class StoreViewModel(
    private val repo: StoreRepository
) : ViewModel() {

    private val _selectedGenres = MutableStateFlow<Set<String>>(emptySet())
    private val _sortBy = MutableStateFlow(SortOption.POPULARITY)

    // Adaptado para o padrão de estado do colega (StoreState e lastMessage)
    private val _state = MutableStateFlow(StoreState())
    val state: StateFlow<StoreState> = _state.asStateFlow()

    private val domainGamesFlow = repo.getGameCatalogStream()
        .map { games -> games.map { it.toGameUi() } }
        .onStart { _state.update { it.copy(isLoading = true, lastMessage = null) } }
        .catch { error ->
            _state.update { it.copy(lastMessage = "Falha ao carregar catálogo: ${error.message}", isLoading = false) }
        }

    init {
        combine(
            domainGamesFlow, _selectedGenres, _sortBy
        ) { gamesUi, genres, sortBy ->

            // Aplica Filtro e Ordenação (Lógica de Negócio)
            val filteredGames = gamesUi
                .filter { game -> genres.isEmpty() || game.genres.any { it in genres } }
            val finalGames = filteredGames.sortedWith(getComparator(sortBy))

            _state.value.copy(
                games = finalGames,
                selectedGenres = genres,
                sortBy = sortBy,
                isLoading = false,
                lastMessage = null
            )
        }.onEach { newState -> _state.value = newState }
            .launchIn(viewModelScope)
    }

    fun reduce(event: StoreEvent) { // Adaptado para 'reduce'
        when (event) {
            is StoreEvent.ApplyGenreFilter -> handleGenreFilter(event.genre)
            is StoreEvent.ChangeSortOrder -> _sortBy.value = event.sortOption
            is StoreEvent.RefreshCatalog -> { /* Implementar lógica de refresh */ }
        }
    }

    private fun handleGenreFilter(genre: String) {
        _selectedGenres.update { currentGenres ->
            if (genre in currentGenres) currentGenres - genre else currentGenres + genre
        }
    }

    private fun getComparator(sortOption: SortOption): Comparator<GameUi> = when (sortOption) {
        SortOption.PRICE_ASC -> compareBy { it.price.removePrefix("R$ ").replace(",", ".").toDoubleOrNull() ?: Double.MAX_VALUE }
        SortOption.PRICE_DESC -> compareByDescending { it.price.removePrefix("R$ ").replace(",", ".").toDoubleOrNull() ?: Double.MIN_VALUE }
        SortOption.RELEASE_DATE -> compareByDescending { it.releaseDate }
        else -> compareByDescending { it.popularityScore }
    }
}