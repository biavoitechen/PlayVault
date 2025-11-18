package com.playvault.contracts

data class GameUi(
    val id: Long,
    val title: String,
    val genres: List<String>,
    val price: String,
    val imageUrl: String?,
    val releaseDate: String?,
    val popularityScore: Int
)
