package com.playvault.data.model

// Modelo principal (usado na lógica e na UI)
data class GameUi(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val isFeatured: Boolean = false
)
