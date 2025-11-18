package com.playvault.data.datasource

import com.playvault.data.dao.GameDao
import com.playvault.data.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface LocalGameDataSource {

    fun observeGames(): Flow<List<GameEntity>>

    fun observeGameById(id: String): Flow<GameEntity?>

    suspend fun upsertGames(games: List<GameEntity>)

    suspend fun upsertGame(game: GameEntity)

    suspend fun clearAll()
}

class LocalGameDataSourceImpl(
    private val gameDao: GameDao
) : LocalGameDataSource {

    override fun observeGames(): Flow<List<GameEntity>> =
        gameDao.observeGames()

    override fun observeGameById(id: String): Flow<GameEntity?> =
        gameDao.observeGameById(id)

    override suspend fun upsertGames(games: List<GameEntity>) {
        gameDao.upsertGames(games)
    }

    override suspend fun upsertGame(game: GameEntity) {
        gameDao.upsertGame(game)
    }

    override suspend fun clearAll() {
        gameDao.clearAll()
    }
}
