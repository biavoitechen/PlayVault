package com.playvault.contracts

data class FriendsState(
    val isLoading: Boolean = false,
    val friends: List<FriendUi> = emptyList(),
    val pendingRequests: List<FriendUi> = emptyList(),
    val error: String? = null
)

sealed interface FriendsEvent {
    data object Refresh : FriendsEvent
    data class SendRequest(val username: String) : FriendsEvent
    data class RejectRequest(val friendId: Long) : FriendsEvent
}
