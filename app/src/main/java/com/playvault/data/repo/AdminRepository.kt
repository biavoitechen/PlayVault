package com.playvault.data.repo

import com.playvault.domain.GameDomain

interface AdminRepository {

    suspend fun getAdminCatalog(): List<GameDomain>

    suspend fun createGame(game: GameDomain)

    suspend fun updateGame(game: GameDomain)

    suspend fun deleteGame(id: Long)

    suspend fun setFeaturedGame(id: Long, isFeatured: Boolean)

    suspend fun refreshRemoteCatalog(): List<GameDomain>
}
