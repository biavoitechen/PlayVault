package com.playvault.data.repo

import com.playvault.data.dao.UserDao
import com.playvault.data.entity.User

class AuthRepository(private val userDao: UserDao) {

    suspend fun register(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun login(email: String, password: String): User? {
        val u = userDao.findByEmail(email)
        return if (u != null && u.password == password) u else null
    }

    fun getAllUsers() = userDao.getAll()
}
