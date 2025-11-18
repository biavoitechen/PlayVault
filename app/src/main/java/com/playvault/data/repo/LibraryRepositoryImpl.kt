package com.playvault.data.repo

import com.playvault.domain.LibraryEntryDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryRepositoryImpl : LibraryRepository {

    private val libraryFlow = MutableStateFlow(seedLibrary())

    override fun getUserLibraryStream(): Flow<List<LibraryEntryDomain>> =
        libraryFlow.asStateFlow()

    override suspend fun installGame(id: Long) {
        val current = libraryFlow.value.toMutableList()
        val idx = current.indexOfFirst { it.gameId == id }
        if (idx >= 0) {
            current[idx] = current[idx].copy(isInstalled = true)
        } else {
            current.add(
                LibraryEntryDomain(
                    id = (current.maxOfOrNull { it.id } ?: 0L) + 1L,
                    gameId = id,
                    gameTitle = "Jogo $id",
                    gameCoverUrl = null,
                    totalPlaytimeMinutes = 0,
                    isInstalled = true
                )
            )
        }
        libraryFlow.value = current
    }

    override suspend fun uninstallGame(id: Long) {
        val current = libraryFlow.value.map {
            if (it.gameId == id) it.copy(isInstalled = false) else it
        }
        libraryFlow.value = current
    }

    override suspend fun updateHoursPlayed(entryId: Long, minutesPlayed: Int) {
        val current = libraryFlow.value.toMutableList()
        val idx = current.indexOfFirst { it.id == entryId }
        if (idx >= 0) {
            current[idx] = current[idx].copy(totalPlaytimeMinutes = minutesPlayed)
            libraryFlow.value = current
        }
    }

    companion object {
        private fun seedLibrary(): List<LibraryEntryDomain> = listOf(
            LibraryEntryDomain(
                id = 1L,
                gameId = 1L,
                gameTitle = "Elden Ring",
                gameCoverUrl = null,
                totalPlaytimeMinutes = 120,
                isInstalled = true
            ),
            LibraryEntryDomain(
                id = 2L,
                gameId = 2L,
                gameTitle = "Stardew Valley",
                gameCoverUrl = null,
                totalPlaytimeMinutes = 45,
                isInstalled = false
            )
        )
    }
}
