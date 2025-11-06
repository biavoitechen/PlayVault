package com.playvault.data.repo

import com.playvault.data.dao.UserDao
import com.playvault.data.entity.User

class AuthRepository(private val userDao: UserDao) {
    suspend fun login(email: String, pass: String): Boolean {
        val u = userDao.findByEmail(email) ?: return false
        return u.password == pass
    }
    suspend fun register(name: String, email: String, pass: String): Result<Long> {
        val exists = userDao.findByEmail(email)
        if (exists != null) return Result.failure(IllegalStateException("Email já cadastrado"))
        val id = userDao.insert(User(name = name, email = email, password = pass))
        return Result.success(id)
    }
}
