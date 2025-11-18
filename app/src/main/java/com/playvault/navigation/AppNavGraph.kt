package com.playvault.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.playvault.ui.screens.DetalheScreen
import com.playvault.ui.screens.LojaScreen

object Routes {
    const val Loja = "loja"
    const val Detalhe = "detalhe/{itemId}"
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Loja
    ) {

        composable(Routes.Loja) {
            LojaScreen(navController = navController)
        }

        composable(Routes.Detalhe) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "1"

            DetalheScreen(
                itemId = itemId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}