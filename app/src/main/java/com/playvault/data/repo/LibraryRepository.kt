package com.playvault.data.repo

import com.playvault.domain.LibraryEntryDomain
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {

    fun getUserLibraryStream(): Flow<List<LibraryEntryDomain>>

    suspend fun installGame(gameId: Long)

    suspend fun uninstallGame(gameId: Long)

    suspend fun updateHoursPlayed(entryId: Long, minutesPlayed: Int)
}
