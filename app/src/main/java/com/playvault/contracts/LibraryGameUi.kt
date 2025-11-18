package com.playvault.contracts

data class LibraryGameUi(
    val id: Long,
    val title: String,
    val coverUrl: String?,
    val hoursPlayed: String,
    val isInstalled: Boolean
)
