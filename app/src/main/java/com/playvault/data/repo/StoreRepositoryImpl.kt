package com.playvault.data.repo

import com.playvault.domain.GameDomain

class StoreRepositoryImpl : StoreRepository {

    override suspend fun getAllGames(): List<GameDomain> {
        return listOf(
            GameDomain(
                id = 1L,
                title = "Elden Ring",
                genres = "RPG, Soulslike",
                price = 299.90,
                coverUrl = null,
                releaseDate = "2022",
                popularityScore = 98
            ),
            GameDomain(
                id = 2L,
                title = "Stardew Valley",
                genres = "Simulação, Fazendinha",
                price = 37.99,
                coverUrl = null,
                releaseDate = "2016",
                popularityScore = 95
            ),
            GameDomain(
                id = 3L,
                title = "Hades",
                genres = "Roguelike, Ação",
                price = 73.99,
                coverUrl = null,
                releaseDate = "2020",
                popularityScore = 92
            )
        )
    }
}
