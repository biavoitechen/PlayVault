package com.playvault.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.playvault.data.dao.FriendDao
import com.playvault.data.dao.GameDao
import com.playvault.data.dao.LibraryEntryDao
import com.playvault.data.dao.UserDao
import com.playvault.data.dao.UserPrefDao
import com.playvault.data.entity.FriendEntity
import com.playvault.data.entity.GameEntity
import com.playvault.data.entity.LibraryEntryEntity
import com.playvault.data.entity.User
import com.playvault.data.entity.UserPrefEntity

@Database(
    entities = [
        User::class,
        GameEntity::class,
        LibraryEntryEntity::class,
        FriendEntity::class,
        UserPrefEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun gameDao(): GameDao
    abstract fun libraryEntryDao(): LibraryEntryDao
    abstract fun friendDao(): FriendDao
    abstract fun userPrefDao(): UserPrefDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val SEED_CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                db.execSQL(
                    """
                    INSERT INTO games (id, title, description, genre, platform, coverUrl, price, isInstalled)
                    VALUES 
                    ('game-elden-ring', 'Elden Ring', 'Action RPG em mundo aberto.', 'RPG', 'PC', NULL, 299.90, 0),
                    ('game-hollow-knight', 'Hollow Knight', 'Metroidvania desafiador.', 'Metroidvania', 'PC', NULL, 37.99, 0),
                    ('game-stardew', 'Stardew Valley', 'Simulador de fazenda e vida.', 'Simulação', 'PC', NULL, 24.99, 0)
                    """.trimIndent()
                )
            }
        }

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "playvault-db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(SEED_CALLBACK)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
