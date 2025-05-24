package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prioridades")
data class Prioridades(
    @PrimaryKey
    val prioridadId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0

)