package com.playvault.domain

data class LibraryEntryDomain(
    val id: Long,
    val gameId: Long,
    val gameTitle: String,
    val gameCoverUrl: String?,
    val totalPlaytimeMinutes: Int,
    val isInstalled: Boolean
)
