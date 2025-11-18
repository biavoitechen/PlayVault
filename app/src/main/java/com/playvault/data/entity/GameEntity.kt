package com.playvault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val genre: String? = null,
    val platform: String? = null,
    val coverUrl: String? = null,
    val price: Double? = null,
    val isInstalled: Boolean = false
)
