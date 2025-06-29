package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Tickets",
    foreignKeys = [
        ForeignKey(
            entity = TecnicoEntity::class,
            parentColumns = ["tecnicoId"],
            childColumns = ["tecnicoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Prioridades::class,
            parentColumns = ["prioridadId"],
            childColumns = ["prioridadId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TicketEntity(
    @PrimaryKey
    val ticketId: Int? = null,
    val fecha: Date = Date(),
    val prioridadId: Int,
    val cliente: String = "",
    val asunto: String = "",
    val descripcion: String = "",
    val tecnicoId: Int
)