package com.playvault.ui.screens

interface LojaEvents {
    fun onComprar(itemId: String)
    fun onPesquisar(query: String)
}

interface BibliotecaEvents {
    fun onAbrir(itemId: String)
    fun onRemover(itemId: String)
}

interface AmigosEvents {
    fun onAbrirPerfil(userId: String)
    fun onBuscarAmigo(query: String)
}

interface PerfilEvents {
    fun onSair()
    fun onIrLogin()
    fun onIrAdmin()
}

interface AuthEvents {
    fun onLogin(email: String, password: String)
    fun onCadastro(nome: String, email: String, password: String)
}
