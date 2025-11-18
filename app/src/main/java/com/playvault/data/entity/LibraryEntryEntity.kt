package com.playvault.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_entries")
data class LibraryEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val gameId: String,
    val hoursPlayed: Int = 0
)
