package com.playvault.data.repo

import com.playvault.domain.GameDomain

interface StoreRepository {
    suspend fun getAllGames(): List<GameDomain>
}
