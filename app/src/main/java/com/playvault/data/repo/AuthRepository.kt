package com.playvault.data.repo

import com.playvault.data.dao.UserDao
import com.playvault.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val dao: UserDao) {
    suspend fun login(email: String, password: String): Boolean = withContext(Dispatchers.IO) {
        dao.login(email, password) != null
    }
    suspend fun register(email: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val exists = dao.getByEmail(email)
        if (exists != null) return@withContext false
        dao.insert(UserEntity(email = email, password = password))
        true
    }
}
