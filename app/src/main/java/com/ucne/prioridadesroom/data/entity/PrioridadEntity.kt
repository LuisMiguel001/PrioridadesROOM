package com.ucne.prioridadesroom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("prioridad_table")
data class PrioridadEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrioridad : Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val plazo: Int = 0,
    val esNulo: Boolean = false,
    val Creador: Int = 0,
    val fechaCreacion: String = "",
    val modidicador: Int = 0,
    val fechaModificacion: String = "",
)
