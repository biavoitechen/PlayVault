package com.playvault.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
<<<<<<< HEAD
import com.playvault.data.entity.UserEntity
=======
import com.playvault.data.entity.User
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
<<<<<<< HEAD
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?
=======
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): User?
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
}
