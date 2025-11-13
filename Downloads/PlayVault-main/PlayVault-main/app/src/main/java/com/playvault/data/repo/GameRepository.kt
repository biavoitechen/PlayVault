package com.playvault.data.repo

import com.playvault.data.model.GameUi
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<List<GameUi>>
    suspend fun syncGames()
    suspend fun addGame(game: GameUi)
    suspend fun updateGame(game: GameUi)
    suspend fun deleteGame(id: String)
}
