// com.playvault.viewmodel/PlayVaultViewModelFactory.kt
package com.playvault.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.playvault.data.repo.* // Importa todos os Repositórios
import com.playvault.viewmodel.admin.AdminViewModel
import com.playvault.viewmodel.friends.FriendsViewModel
import com.playvault.viewmodel.library.LibraryViewModel
import com.playvault.viewmodel.store.StoreViewModel

/**
 * Fábrica Central da Pessoa 2, responsável por injetar dependências em ViewModels
 * que NÃO são o AuthViewModel (pois este usa a fábrica do colega).
 */
class PlayVaultViewModelFactory(
    private val storeRepo: StoreRepository,
    private val libraryRepo: LibraryRepository,
    private val friendsRepo: FriendsRepository,
    private val adminRepo: AdminRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // OBS: AuthViewModel OMITIDO para usar a fábrica específica do colega

            modelClass.isAssignableFrom(StoreViewModel::class.java) -> {
                StoreViewModel(storeRepo) as T
            }
            modelClass.isAssignableFrom(LibraryViewModel::class.java) -> {
                LibraryViewModel(libraryRepo) as T
            }
            modelClass.isAssignableFrom(FriendsViewModel::class.java) -> {
                FriendsViewModel(friendsRepo) as T
            }
            modelClass.isAssignableFrom(AdminViewModel::class.java) -> {
                AdminViewModel(adminRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class. Use the specific AuthVMFactory for AuthViewModel.")
        }
    }
}