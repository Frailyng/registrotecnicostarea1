package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Compras")
class CompraEntity (
    @PrimaryKey
    val compraId: Int?,
    val descripcion: String,
    val monto: Double
)