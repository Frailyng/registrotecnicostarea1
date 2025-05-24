package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object TecnicoList: Screen()
    @Serializable
    data class Tecnico(val tecnicoId: Int?) : Screen()
    @Serializable
    data object TicketsList : Screen()
    @Serializable
    data class Tickets(val ticketId: Int?) : Screen()
    @Serializable
    data object PrioridadList: Screen()
    @Serializable
    data class Prioridad(val prioridadId: Int?) : Screen()
}