package com.playvault.domain

data class GameDomain(
    val id: Long,
    val title: String,
    val genres: String,
    val price: Double,
    val coverUrl: String?,
    val releaseDate: String?,
    val popularityScore: Int
)
