package com.playvault.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomItem(
    val route: Route,
    val label: String,
    val icon: @Composable () -> Unit
)

private val items = listOf(
    BottomItem(Route.Loja, "Loja", { Icon(Icons.Default.Home, null) }),
    BottomItem(Route.Biblioteca, "Biblioteca", { Icon(Icons.Default.LibraryBooks, null) }),
    BottomItem(Route.Amigos, "Amigos", { Icon(Icons.Default.Group, null) }),
    BottomItem(Route.Perfil, "Perfil", { Icon(Icons.Default.Person, null) }),
)

@Composable
fun PlayVaultBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        items.forEach { item ->
            val selected = currentDestination.isOnDestination(item.route.path)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route.path) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                icon = item.icon,
                label = { Text(item.label) }
            )
        }
    }
}

private fun NavDestination?.isOnDestination(target: String): Boolean =
    this?.hierarchy?.any { it.route == target } == true
