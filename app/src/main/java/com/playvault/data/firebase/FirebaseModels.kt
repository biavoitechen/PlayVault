package com.playvault.data.firebase

data class GameRemote(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val isFeatured: Boolean = false
)
