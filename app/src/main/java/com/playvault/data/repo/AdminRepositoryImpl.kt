package com.playvault.data.repo

import com.playvault.domain.GameDomain
import kotlinx.coroutines.delay


class AdminRepositoryImpl : AdminRepository {

    private val catalog = mutableListOf(
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

    override suspend fun getAdminCatalog(): List<GameDomain> {
        delay(150)
        return catalog.toList()
    }

    override suspend fun createGame(game: GameDomain) {
        val nextId = (catalog.maxOfOrNull { it.id } ?: 0L) + 1L
        catalog += game.copy(id = nextId)
        delay(150)
    }

    override suspend fun updateGame(game: GameDomain) {
        val index = catalog.indexOfFirst { it.id == game.id }
        if (index != -1) {
            catalog[index] = game
        }
        delay(150)
    }

    override suspend fun deleteGame(id: Long) {
        catalog.removeIf { it.id == id }
        delay(150)
    }

    override suspend fun setFeaturedGame(id: Long, isFeatured: Boolean) {
        val index = catalog.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = catalog[index]
            val boosted = if (isFeatured) {
                current.copy(popularityScore = current.popularityScore + 5)
            } else {
                current.copy(popularityScore = current.popularityScore - 5)
            }
            catalog[index] = boosted
        }
        delay(150)
    }

    override suspend fun refreshRemoteCatalog(): List<GameDomain> {
        delay(200)
        return catalog.toList()
    }
}
