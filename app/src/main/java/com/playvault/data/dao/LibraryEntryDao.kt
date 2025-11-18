package com.playvault.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.playvault.data.entity.LibraryEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryEntryDao {

    @Query("SELECT * FROM library_entries")
    fun observeLibrary(): Flow<List<LibraryEntryEntity>>

    @Query("SELECT * FROM library_entries WHERE gameId = :gameId LIMIT 1")
    fun observeEntryForGame(gameId: String): Flow<LibraryEntryEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertEntry(entry: LibraryEntryEntity)

    @Delete
    suspend fun deleteEntry(entry: LibraryEntryEntity)

    @Query("DELETE FROM library_entries WHERE gameId = :gameId")
    suspend fun deleteByGameId(gameId: String)
}
