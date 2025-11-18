package com.playvault.contracts

data class LibraryState(
    val isLoading: Boolean = false,
    val items: List<LibraryGameUi> = emptyList(),
    val error: String? = null
)

sealed interface LibraryEvent {
    data object Refresh : LibraryEvent
    data class InstallGame(val gameId: Long) : LibraryEvent
    data class UninstallGame(val gameId: Long) : LibraryEvent
    data class UpdateHoursPlayed(val gameId: Long, val minutes: Int) : LibraryEvent
}
