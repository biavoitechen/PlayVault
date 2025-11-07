package com.playvault.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playvault.data.repo.AuthRepository
import com.playvault.di.ServiceLocator
import com.playvault.ui.screens.*
import com.playvault.ui.viewmodel.rememberAuthViewModel

object Routes {
    const val Loja = "loja"
    const val Biblioteca = "biblioteca"
    const val Amigos = "amigos"
    const val Perfil = "perfil"
    const val LoginCadastro = "loginCadastro"
    const val Admin = "admin"
    const val Detalhe = "detalhe/{itemId}"
}

@Composable
fun AppNavGraph(navController: NavHostController, context: Context) {
    val repo: AuthRepository = ServiceLocator.provideAuthRepository(context)
    val authVm = rememberAuthViewModel(repo)

    NavHost(navController = navController, startDestination = Routes.LoginCadastro) {
        composable(Routes.LoginCadastro) {
            LoginCadastroScreen(vm = authVm) {
                navController.navigate(Routes.Loja) {
                    popUpTo(Routes.LoginCadastro) { inclusive = true }
                }
            }
        }
        composable(Routes.Loja) {
            LojaScreen(onClickDetalhe = { id -> navController.navigate("detalhe/$id") })
        }
        composable(Routes.Biblioteca) { BibliotecaScreen() }
        composable(Routes.Amigos) { AmigosScreen() }
        composable(Routes.Perfil) {
            PerfilScreen(vm = authVm,
                onGotoLogin = { navController.navigate(Routes.LoginCadastro) },
                onGotoAdmin = { navController.navigate(Routes.Admin) })
        }
        composable(Routes.Admin) { AdminScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.Detalhe) { backStack ->
            val id = backStack.arguments?.getString("itemId") ?: ""
            DetalheScreen(itemId = id, onBack = { navController.popBackStack() })
        }
    }
}
