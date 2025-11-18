package com.playvault.data.datasource

import com.playvault.data.dao.LibraryEntryDao
import com.playvault.data.entity.LibraryEntryEntity
import kotlinx.coroutines.flow.Flow

interface LocalLibraryDataSource {

    fun observeLibrary(): Flow<List<LibraryEntryEntity>>

    fun observeEntryForGame(gameId: String): Flow<LibraryEntryEntity?>

    suspend fun upsertEntry(entry: LibraryEntryEntity)

    suspend fun deleteEntry(entry: LibraryEntryEntity)

    suspend fun deleteByGameId(gameId: String)
}

class LocalLibraryDataSourceImpl(
    private val dao: LibraryEntryDao
) : LocalLibraryDataSource {

    override fun observeLibrary(): Flow<List<LibraryEntryEntity>> =
        dao.observeLibrary()

    override fun observeEntryForGame(gameId: String): Flow<LibraryEntryEntity?> =
        dao.observeEntryForGame(gameId)

    override suspend fun upsertEntry(entry: LibraryEntryEntity) {
        dao.upsertEntry(entry)
    }

    override suspend fun deleteEntry(entry: LibraryEntryEntity) {
        dao.deleteEntry(entry)
    }

    override suspend fun deleteByGameId(gameId: String) {
        dao.deleteByGameId(gameId)
    }
}
