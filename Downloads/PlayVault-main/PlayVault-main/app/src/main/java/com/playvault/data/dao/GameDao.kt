package com.playvault.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.playvault.data.entity.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("DELETE FROM games")
    suspend fun deleteAll()
}
