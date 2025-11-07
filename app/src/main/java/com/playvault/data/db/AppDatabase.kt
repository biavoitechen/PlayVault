package com.playvault.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.playvault.data.dao.UserDao
import com.playvault.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
