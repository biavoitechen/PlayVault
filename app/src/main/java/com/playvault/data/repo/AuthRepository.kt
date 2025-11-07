package com.playvault.data.repo

import com.playvault.data.dao.UserDao
<<<<<<< HEAD
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
=======
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
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
    }
}
