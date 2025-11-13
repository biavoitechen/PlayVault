package com.playvault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games") // Define a tabela 'games' para o Room
data class GameEntity(
    @PrimaryKey(autoGenerate = true) // 1. ID é Int e gerado automaticamente
    val id: Int = 0,

    // 2. Parâmetros correspondem ao uso no repositório
    val name: String,
    val description: String,
    val imageUrl: String,
    val isFeatured: Boolean
)