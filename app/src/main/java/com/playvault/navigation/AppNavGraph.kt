// app/src/main/java/com/playvault/navigation/AppNavGraph.kt
package com.playvault.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playvault.di.ServiceLocator
import com.playvault.ui.screens.AdminScreen
import com.playvault.ui.screens.AmigosScreen
import com.playvault.ui.screens.BibliotecaScreen
import com.playvault.ui.screens.DetalheScreen
import com.playvault.ui.screens.LojaScreen
import com.playvault.ui.screens.LoginCadastroScreen
import com.playvault.ui.screens.PerfilScreen
import com.playvault.ui.viewmodel.AuthVMFactory
import com.playvault.viewmodel.auth.AuthViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    context: Context
) {
    val repo = ServiceLocator.provideAuthRepository(context)

    val authVm: AuthViewModel = viewModel(
        factory = AuthVMFactory(repo)
    )

    NavHost(
        navController = navController,
        startDestination = Route.Login.path
    ) {
        composable(Route.Login.path) {
            LoginCadastroScreen(
                vm = authVm,
                onAuthOk = {
                    navController.navigate(Route.Loja.path) {
                        popUpTo(Route.Login.path) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.Loja.path) {
            LojaScreen(navController = navController)
        }

        composable(Route.Biblioteca.path) {
            BibliotecaScreen(navController = navController)
        }

        composable(Route.Amigos.path) {
            AmigosScreen(navController = navController)
        }

        composable(Route.Perfil.path) {
            PerfilScreen(navController = navController, vm = authVm)
        }

        composable(Route.Admin.path) {
            AdminScreen(navController = navController, vm = authVm)
        }

        composable(Route.Detalhe.path) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(Route.Detalhe.ARG_ITEM_ID) ?: ""
            DetalheScreen(navController = navController, itemId = itemId)
        }
    }
}
