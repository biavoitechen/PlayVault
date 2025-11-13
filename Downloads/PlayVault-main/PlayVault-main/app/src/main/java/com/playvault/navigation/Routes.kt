package com.playvault.navigation

sealed class Route(val path: String) {
    data object Loja : Route("loja")
    data object Biblioteca : Route("biblioteca")
    data object Amigos : Route("amigos")
    data object Perfil : Route("perfil")

    data object Login : Route("login")
    data object Cadastro : Route("cadastro")
    data object Admin : Route("admin")
    data object Detalhe : Route("detalhe/{itemId}") {
        fun build(itemId: String) = "detalhe/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }
}

val bottomDestinations = listOf(
    Route.Loja,
    Route.Biblioteca,
    Route.Amigos,
    Route.Perfil
)

fun isBottomDestination(route: String?): Boolean =
    route != null && bottomDestinations.any { it.path == route }
