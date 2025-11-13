// com.playvault.viewmodel.friends/FriendsViewModel.kt
package com.playvault.viewmodel.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playvault.data.repo.FriendsRepository
import com.playvault.contracts.*
import com.playvault.util.UiMapper.toFriendUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FriendsViewModel(
    private val repo: FriendsRepository
) : ViewModel() {

    private val friendsFlow = repo.getFriendsListStream().map { list -> list.map { it.toFriendUi() } }
    private val requestsFlow = repo.getPendingRequestsStream().map { list -> list.map { it.toFriendUi() } }

    private val _state = MutableStateFlow(FriendsState())
    val state: StateFlow<FriendsState> = _state.asStateFlow()

    init {
        combine(
            friendsFlow, requestsFlow
        ) { friends, requests ->
            _state.value.copy(
                friendsList = friends,
                pendingRequests = requests,
                isLoading = false,
                lastMessage = null
            )
        }.onStart { _state.update { it.copy(isLoading = true) } }
            .catch { error -> _state.update { it.copy(lastMessage = "Erro de amigos: ${error.message}", isLoading = false) } }
            .onEach { newState -> _state.value = newState }
            .launchIn(viewModelScope)
    }


    fun reduce(event: FriendsEvent) {
        when (event) {
            is FriendsEvent.SendRequest -> handleApiAction { repo.sendFriendRequest(event.targetUsername) }
            is FriendsEvent.AcceptRequest -> handleApiAction { repo.acceptFriendRequest(event.requestId) }
            is FriendsEvent.RejectRequest -> handleApiAction { repo.rejectFriendRequest(event.requestId) }
            is FriendsEvent.RemoveFriend -> handleApiAction { repo.removeFriend(event.friendId) }
        }
    }

    private fun handleApiAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, lastMessage = null) }
            try {
                action()
                _state.update { it.copy(isLoading = false, lastMessage = "Ação concluída.") }
            } catch (e: Exception) {
                _state.update { it.copy(lastMessage = "Erro na ação: ${e.message}", isLoading = false) }
            }
        }
    }
}