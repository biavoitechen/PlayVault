package com.playvault.navigation

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
        }
    }
}
