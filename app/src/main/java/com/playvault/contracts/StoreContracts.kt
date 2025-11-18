// app/src/main/java/com/playvault/contracts/StoreContracts.kt
package com.playvault.contracts

/**
 * Opções de ordenação do catálogo da Loja.
 */
enum class SortOption {
    BY_POPULARITY,
    BY_TITLE
}

data class StoreState(
    val isLoading: Boolean = false,
    val games: List<GameUi> = emptyList(),
    val sortOption: SortOption = SortOption.BY_POPULARITY,
    val lastMessage: String? = null
)

sealed class StoreEvent {
    data object Refresh : StoreEvent()
    data class ChangeSort(val option: SortOption) : StoreEvent()
    data class GameClicked(val gameId: Long) : StoreEvent()
}
