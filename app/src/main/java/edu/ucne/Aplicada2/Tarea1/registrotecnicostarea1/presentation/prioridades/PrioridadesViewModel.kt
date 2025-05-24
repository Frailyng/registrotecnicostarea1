package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.PrioridadadesRepository
import kotlinx.coroutines.launch

class PrioridadesViewModel(
    private val prioridadadesRepository: PrioridadadesRepository
): ViewModel() {
    fun savePrioridad(prioridad: Prioridades) {
        viewModelScope.launch {
            prioridadadesRepository.save(prioridad)
        }
    }

    suspend fun findPrioridad(id: Int): Prioridades? {
        return prioridadadesRepository.find(id)
    }

    fun deletePrioridad(prioridad: Prioridades) {
        viewModelScope.launch {
            prioridadadesRepository.delete(prioridad)
        }
    }
}