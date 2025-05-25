package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import java.util.Date

data class TicketUiState(
    val ticketId: Int? = null,
    val fecha: Date = Date(),
    val prioridadId: Int,
    val cliente: String = "",
    val asunto: String = "",
    val descripcion: String = "",
    val tecnicoId: Int,
    val errorMessage: String? = null,
    val tickets: List<TicketEntity> = emptyList(),
    val tecnicos: List<TecnicoEntity> = emptyList(),
    val prioridades: List<Prioridades> = emptyList()
)