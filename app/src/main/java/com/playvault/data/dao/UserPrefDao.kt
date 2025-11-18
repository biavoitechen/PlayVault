package com.playvault.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.playvault.data.entity.UserPrefEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPrefDao {

    @Query("SELECT * FROM user_prefs WHERE `key` = :key LIMIT 1")
    fun observePref(key: String): Flow<UserPrefEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPref(pref: UserPrefEntity)

    @Query("DELETE FROM user_prefs WHERE `key` = :key")
    suspend fun deletePref(key: String)
}
