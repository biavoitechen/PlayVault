package com.playvault.contracts

import com.playvault.data.model.GameUi

data class AdminState(
    val games: List<GameUi> = emptyList(),
    val isLoading: Boolean = false,
    val lastMessage: String? = null
)
