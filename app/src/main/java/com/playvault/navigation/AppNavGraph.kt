package com.playvault.navigation

<<<<<<< HEAD
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
=======
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.playvault.ui.screens.*

@Composable
fun AppNav(modifier: Modifier = Modifier) {
    val nav = rememberNavController()
    PlayVaultScaffold(navController = nav) { hostModifier ->
        NavHost(
            navController = nav,
            startDestination = Route.Loja.path,
            modifier = modifier.then(hostModifier)
        ) {
            // Tabs
            composable(Route.Loja.path) {
                LojaScreen(onClickDetalhe = { id -> nav.navigate(Route.Detalhe.build(id)) })
            }
            composable(Route.Biblioteca.path) { BibliotecaScreen() }
            composable(Route.Amigos.path) { AmigosScreen() }
            composable(Route.Perfil.path) {
                PerfilScreen(
                    onClickLogin = { nav.navigate(Route.Login.path) },
                    onClickAdmin = { nav.navigate(Route.Admin.path) }
                )
            }

            // Secundárias
            composable(Route.Login.path) {
                LoginScreen(
                    onLoginOk = {
                        nav.navigate(Route.Loja.path) { popUpTo(0) }
                    },
                    onClickCadastro = { nav.navigate(Route.Cadastro.path) }
                )
            }
            composable(Route.Cadastro.path) {
                CadastroScreen(onCadastroOk = { nav.popBackStack() })
            }
            composable(
                route = Route.Detalhe.path,
                arguments = listOf(navArgument(Route.Detalhe.ARG_ITEM_ID) {
                    type = NavType.StringType
                })
            ) { back ->
                val itemId = back.arguments?.getString(Route.Detalhe.ARG_ITEM_ID) ?: ""
                DetalheScreen(itemId = itemId)
            }
            composable(Route.Admin.path) { AdminScreen(onClose = { nav.popBackStack() }) }
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
        }
    }
}
