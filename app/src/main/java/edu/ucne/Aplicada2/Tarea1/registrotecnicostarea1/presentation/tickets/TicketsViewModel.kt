package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TicketsRepository
import kotlinx.coroutines.launch

class TicketViewModel(
    private val ticketsRepository: TicketsRepository
) : ViewModel() {
    fun saveTicket(ticket: TicketEntity) {
        viewModelScope.launch {
            ticketsRepository.save(ticket)
        }
    }

    suspend fun findTicket(id: Int): TicketEntity? {
        return ticketsRepository.find(id)
    }

    fun deleteTicket(ticket: TicketEntity) {
        viewModelScope.launch {
            ticketsRepository.delete(ticket)
        }
    }
}