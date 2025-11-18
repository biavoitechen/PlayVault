package com.playvault.domain

data class FriendDomain(
    val id: Long,
    val username: String,
    val isOnline: Boolean,
    val isPlayingGame: String?,
    val avatarUrl: String?
)
