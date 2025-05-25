package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.mensajes

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.MensajeEntity
import java.util.Date

data class MensajeUiState(
    val mensajeId: Int? = null,
    val fecha: Date = Date(),
    val contenido: String? = null,
    val remitente: String? = null,
    val tipoRemitente: String? = null,
    val ticketId: Int? = 0,
    val errorMessage: String? = null,
    val mensajes: List<MensajeEntity> = emptyList()
)