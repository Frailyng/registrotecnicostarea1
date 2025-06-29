package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tecnicos")
data class TecnicoEntity(
    @PrimaryKey
    val tecnicoId: Int? = null,
    val nombre: String = "",
    val sueldo: Double = 0.0

)