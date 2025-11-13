package com.playvault.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayVaultScaffold(
    navController: NavHostController,
    content: @Composable (Modifier) -> Unit
) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottom = isBottomDestination(route)

    Scaffold(
        bottomBar = { if (showBottom) PlayVaultBottomBar(navController) }
    ) { inner ->
        content(Modifier.padding(inner))
    }
}
