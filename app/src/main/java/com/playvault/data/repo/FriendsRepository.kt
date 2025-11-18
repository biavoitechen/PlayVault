package com.playvault.data.repo

import com.playvault.domain.FriendDomain
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {

    fun getFriendsListStream(): Flow<List<FriendDomain>>

    fun getPendingRequestsStream(): Flow<List<FriendDomain>>

    suspend fun sendFriendRequest(friendId: Long)

    suspend fun acceptFriendRequest(friendId: Long)

    suspend fun removeFriend(friendId: Long)
}
