package com.playvault.contracts

import com.playvault.data.model.GameUi

sealed class AdminEvent {
    data class CreateGameClicked(val gameData: GameUi) : AdminEvent()
    data class EditGameClicked(val gameData: GameUi) : AdminEvent()
    data class DeleteGameClicked(val gameId: String) : AdminEvent()
    data class ToggleHighlight(val gameId: String, val isFeatured: Boolean) : AdminEvent()
    object RefreshCatalog : AdminEvent()
}
