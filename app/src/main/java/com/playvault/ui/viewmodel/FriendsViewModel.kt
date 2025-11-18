package com.playvault.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.entity.FriendEntity
import com.playvault.data.repo.GameLibraryRepository
import com.playvault.data.repo.GameLibraryRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FriendUi(
    val id: String,
    val name: String
)

data class FriendsUiState(
    val isLoading: Boolean = true,
    val friends: List<FriendUi> = emptyList(),
    val pending: List<FriendUi> = emptyList()
)

class FriendsViewModel(
    private val repository: GameLibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState: StateFlow<FriendsUiState> = _uiState

    init {
        observeFriends()
    }

    private fun observeFriends() {
        viewModelScope.launch {
            combine(
                repository.observeFriends(),
                repository.observePendingFriends()
            ) { accepted, pending ->
                Pair(accepted, pending)
            }.collectLatest { (accepted, pending) ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        friends = accepted.map { f -> f.toUi() },
                        pending = pending.map { f -> f.toUi() }
                    )
                }
            }
        }
    }

    private fun FriendEntity.toUi(): FriendUi =
        FriendUi(
            id = id,
            name = name
        )

    fun addFriend(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.addFriend(name.trim())
        }
    }

    fun acceptFriend(friendId: String) {
        viewModelScope.launch {
            repository.acceptFriend(friendId)
        }
    }

    fun removeFriend(friendId: String) {
        viewModelScope.launch {
            repository.removeFriend(friendId)
        }
    }
}

@Composable
fun rememberFriendsViewModel(
    context: Context = LocalContext.current
): FriendsViewModel {
    val appContext = context.applicationContext
    return remember {
        val repo: GameLibraryRepository = GameLibraryRepositoryProvider.provide(appContext)
        FriendsViewModel(repo)
    }
}
