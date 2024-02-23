package com.ucne.prioridadesroom.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.prioridadesroom.data.dao.PrioridadDao
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import com.ucne.prioridadesroom.data.repository.PrioridadRepository

@Database(
    entities = [PrioridadEntity::class],
    version = 1
)
abstract class PrioridadDatabase: RoomDatabase(){

    abstract fun prioridadDao(): PrioridadDao

}