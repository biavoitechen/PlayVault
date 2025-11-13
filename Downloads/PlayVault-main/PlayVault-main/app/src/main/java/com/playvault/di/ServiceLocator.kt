package com.playvault.di

import android.content.Context
import androidx.room.Room
import com.playvault.data.db.AppDatabase
import com.playvault.data.repo.AuthRepository

object ServiceLocator {
    @Volatile private var db: AppDatabase? = null
    fun provideAuthRepository(context: Context): AuthRepository {
        val instance = db ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "playvault.db"
            ).build().also { db = it }
        }
        return AuthRepository(instance.userDao())
    }
}
