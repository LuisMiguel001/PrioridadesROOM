package com.ucne.prioridadesroom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrioridadDao {

    @Upsert()
    suspend fun upsert(prioridad: PrioridadEntity)

    @Delete
    suspend fun delete(prioridad: PrioridadEntity)

    @Query("Select * From prioridad_table")
    fun getAll(): Flow<List<PrioridadEntity>>
}