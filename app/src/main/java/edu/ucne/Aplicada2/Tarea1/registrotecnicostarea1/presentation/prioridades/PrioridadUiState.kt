package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades

data class PrioridadUiState(
    val prioridadId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0,
    val errorMessage: String? = null,
    val prioridades: List<Prioridades> = emptyList()
)
