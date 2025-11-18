package com.playvault.data.repo

import android.content.Context
import com.playvault.data.db.AppDatabase
import com.playvault.data.datasource.LocalFriendsDataSource
import com.playvault.data.datasource.LocalFriendsDataSourceImpl
import com.playvault.data.datasource.LocalGameDataSource
import com.playvault.data.datasource.LocalGameDataSourceImpl
import com.playvault.data.datasource.LocalLibraryDataSource
import com.playvault.data.datasource.LocalLibraryDataSourceImpl
import com.playvault.data.datasource.LocalUserPrefDataSource
import com.playvault.data.datasource.LocalUserPrefDataSourceImpl
import com.playvault.data.entity.FriendEntity
import com.playvault.data.entity.GameEntity
import com.playvault.data.entity.LibraryEntryEntity
import com.playvault.data.entity.UserPrefEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

interface GameLibraryRepository {

    fun observeStoreGames(): Flow<List<GameEntity>>

    fun observeLibraryEntries(): Flow<List<LibraryEntryEntity>>
    suspend fun installGame(gameId: String)
    suspend fun uninstallGame(gameId: String)

    // Amigos
    fun observeFriends(): Flow<List<FriendEntity>>
    fun observePendingFriends(): Flow<List<FriendEntity>>
    suspend fun addFriend(name: String)
    suspend fun acceptFriend(friendId: String)
    suspend fun removeFriend(friendId: String)

    fun observePref(key: String): Flow<UserPrefEntity?>
    suspend fun savePref(key: String, value: String)
    suspend fun deletePref(key: String)
}

class GameLibraryRepositoryImpl(
    private val gameDataSource: LocalGameDataSource,
    private val libraryDataSource: LocalLibraryDataSource,
    private val friendsDataSource: LocalFriendsDataSource,
    private val prefsDataSource: LocalUserPrefDataSource
) : GameLibraryRepository {


    override fun observeStoreGames(): Flow<List<GameEntity>> =
        gameDataSource.observeGames()


    override fun observeLibraryEntries(): Flow<List<LibraryEntryEntity>> =
        libraryDataSource.observeLibrary()

    override suspend fun installGame(gameId: String) {
        val entry = LibraryEntryEntity(
            gameId = gameId,
            hoursPlayed = 0
        )
        libraryDataSource.upsertEntry(entry)
    }

    override suspend fun uninstallGame(gameId: String) {
        libraryDataSource.deleteByGameId(gameId)
    }


    override fun observeFriends(): Flow<List<FriendEntity>> =
        friendsDataSource.observeFriends()
            .map { list -> list.filter { it.isAccepted } }

    override fun observePendingFriends(): Flow<List<FriendEntity>> =
        friendsDataSource.observeFriends()
            .map { list -> list.filter { !it.isAccepted } }

    override suspend fun addFriend(name: String) {
        if (name.isBlank()) return
        val friend = FriendEntity(
            id = UUID.randomUUID().toString(),
            name = name.trim(),
            avatarUrl = null,
            isAccepted = false
        )
        friendsDataSource.upsertFriend(friend)
    }

    override suspend fun acceptFriend(friendId: String) {
        val existing = friendsDataSource.getFriendById(friendId) ?: return
        friendsDataSource.upsertFriend(existing.copy(isAccepted = true))
    }

    override suspend fun removeFriend(friendId: String) {
        friendsDataSource.deleteById(friendId)
    }


    override fun observePref(key: String): Flow<UserPrefEntity?> =
        prefsDataSource.observePref(key)

    override suspend fun savePref(key: String, value: String) {
        prefsDataSource.upsertPref(UserPrefEntity(key = key, value = value))
    }

    override suspend fun deletePref(key: String) {
        prefsDataSource.deletePref(key)
    }
}

object GameLibraryRepositoryProvider {

    fun provide(context: Context): GameLibraryRepository {
        val db = AppDatabase.getInstance(context)

        val gameDs: LocalGameDataSource = LocalGameDataSourceImpl(db.gameDao())
        val libraryDs: LocalLibraryDataSource = LocalLibraryDataSourceImpl(db.libraryEntryDao())
        val friendsDs: LocalFriendsDataSource = LocalFriendsDataSourceImpl(db.friendDao())
        val prefsDs: LocalUserPrefDataSource = LocalUserPrefDataSourceImpl(db.userPrefDao())

        return GameLibraryRepositoryImpl(
            gameDataSource = gameDs,
            libraryDataSource = libraryDs,
            friendsDataSource = friendsDs,
            prefsDataSource = prefsDs
        )
    }
}
