package com.playvault.data.datasource

import com.playvault.data.dao.UserPrefDao
import com.playvault.data.entity.UserPrefEntity
import kotlinx.coroutines.flow.Flow

interface LocalUserPrefDataSource {

    fun observePref(key: String): Flow<UserPrefEntity?>

    suspend fun upsertPref(pref: UserPrefEntity)

    suspend fun deletePref(key: String)
}

class LocalUserPrefDataSourceImpl(
    private val dao: UserPrefDao
) : LocalUserPrefDataSource {

    override fun observePref(key: String): Flow<UserPrefEntity?> =
        dao.observePref(key)

    override suspend fun upsertPref(pref: UserPrefEntity) {
        dao.upsertPref(pref)
    }

    override suspend fun deletePref(key: String) {
        dao.deletePref(key)
    }
}
