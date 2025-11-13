package com.playvault.data.repo

import com.playvault.model.GameUi

interface AdminRepository {
    suspend fun fetchRemoteCatalog(): List<GameUi>
    suspend fun createGame(game: GameUi)
    suspend fun updateGame(game: GameUi)
    suspend fun deleteGame(gameId: String)
    suspend fun setFeaturedGame(gameId: String, isFeatured: Boolean)
}
