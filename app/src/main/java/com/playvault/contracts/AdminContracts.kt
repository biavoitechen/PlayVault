package com.playvault.contracts

data class AdminState(
    val isLoading: Boolean = false,
    val games: List<GameUi> = emptyList(),
    val featuredGameId: Long? = null,
    val lastMessage: String? = null,
    val error: String? = null
)

sealed interface AdminEvent {
    data object Refresh : AdminEvent
    data class CreateGame(val game: GameUi) : AdminEvent
    data class UpdateGame(val game: GameUi) : AdminEvent
    data class DeleteGame(val id: Long) : AdminEvent
    data class SetFeaturedGame(val id: Long) : AdminEvent
}
