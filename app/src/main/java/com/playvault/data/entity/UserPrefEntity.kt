package com.playvault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_prefs")
data class UserPrefEntity(
    @PrimaryKey
    val key: String,
    val value: String
)
