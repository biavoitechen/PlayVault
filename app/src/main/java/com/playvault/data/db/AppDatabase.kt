package com.playvault.data.db

<<<<<<< HEAD
import androidx.room.Database
import androidx.room.RoomDatabase
import com.playvault.data.dao.UserDao
import com.playvault.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
=======
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.playvault.data.dao.UserDao
import com.playvault.data.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "playvault.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // pré-popula um usuário demo
                        CoroutineScope(Dispatchers.IO).launch {
                            get(context).userDao().insert(
                                User(name = "Demo", email = "demo@playvault.app", password = "123456")
                            )
                        }
                    }
                }).build().also { INSTANCE = it }
            }
    }
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
}
