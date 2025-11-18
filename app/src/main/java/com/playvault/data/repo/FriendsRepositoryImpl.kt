package com.playvault.data.repo

import com.playvault.domain.FriendDomain
import kotlinx.coroutines.delay


class FriendsRepositoryImpl : FriendsRepository {

    private val friends = mutableListOf(
        FriendDomain(
            id = 1L,
            username = "Raphael",
            isOnline = true,
            isPlayingGame = "Elden Ring",
            avatarUrl = null
        ),
        FriendDomain(
            id = 2L,
            username = "Bianca",
            isOnline = false,
            isPlayingGame = null,
            avatarUrl = null
        ),
        FriendDomain(
            id = 3L,
            username = "Henrique",
            isOnline = true,
            isPlayingGame = "Stardew Valley",
            avatarUrl = null
        )
    )

    suspend fun getFriendsList(): List<FriendDomain> {
        delay(200)
        return friends.toList()
    }

    suspend fun sendFriendRequest(targetUsername: String) {
        delay(150)
    }

    suspend fun acceptRequest(requestId: Long) {
        delay(100)
    }

    suspend fun rejectRequest(requestId: Long) {
        delay(100)
    }

    override suspend fun removeFriend(friendId: Long) {
        friends.removeIf { it.id == friendId }
        delay(100)
    }
}
