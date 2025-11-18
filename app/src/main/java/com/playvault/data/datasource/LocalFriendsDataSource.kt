package com.playvault.data.datasource

import com.playvault.data.dao.FriendDao
import com.playvault.data.entity.FriendEntity
import kotlinx.coroutines.flow.Flow

interface LocalFriendsDataSource {
    fun observeFriends(): Flow<List<FriendEntity>>
    suspend fun getFriendById(friendId: String): FriendEntity?
    suspend fun upsertFriend(friend: FriendEntity)
    suspend fun deleteFriend(friend: FriendEntity)
    suspend fun deleteById(friendId: String)
}

class LocalFriendsDataSourceImpl(
    private val dao: FriendDao
) : LocalFriendsDataSource {

    override fun observeFriends(): Flow<List<FriendEntity>> =
        dao.observeFriends()

    override suspend fun getFriendById(friendId: String): FriendEntity? =
        dao.getFriendById(friendId)

    override suspend fun upsertFriend(friend: FriendEntity) {
        dao.upsertFriend(friend)
    }

    override suspend fun deleteFriend(friend: FriendEntity) {
        dao.deleteFriend(friend)
    }

    override suspend fun deleteById(friendId: String) {
        dao.deleteById(friendId)
    }
}
