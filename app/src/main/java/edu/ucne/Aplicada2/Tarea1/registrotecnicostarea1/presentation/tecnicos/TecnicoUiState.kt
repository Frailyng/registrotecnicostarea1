package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity

data class TecnicoUiState(
    val tecnicoId: Int? = null,
    val nombre: String = "",
    val sueldo: Double = 0.0,
    val errorMessage: String? = null,
    val tecnicos: List<TecnicoEntity> = emptyList()
)
