package com.ucne.prioridadesroom.data.repository

import androidx.room.Dao
import com.ucne.prioridadesroom.data.dao.PrioridadDao
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrioridadRepository @Inject constructor(
    private val prioridadDao: PrioridadDao
) {
    suspend fun upsert(prioridadEntity: PrioridadEntity) {
        prioridadDao.upsert(prioridadEntity)
    }

    suspend fun delete(prioridadEntity: PrioridadEntity) {
        prioridadDao.delete(prioridadEntity)
    }

    suspend fun getPrioridad(): Flow<List<PrioridadEntity>> {
        return prioridadDao.getAll()
    }
}