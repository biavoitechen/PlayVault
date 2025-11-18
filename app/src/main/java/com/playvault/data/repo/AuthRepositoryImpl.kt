package com.playvault.data.repo

class AuthRepository {

    private val users = mutableMapOf<String, String>()

    suspend fun login(email: String, password: String): Boolean {
        val key = email.trim()
        val savedPassword = users[key]
        return savedPassword != null && savedPassword == password
    }

    suspend fun register(email: String, password: String): Boolean {
        val key = email.trim()

        if (key.isBlank() || password.isBlank()) return false
        if (users.containsKey(key)) return false

        users[key] = password
        return true
    }
}
