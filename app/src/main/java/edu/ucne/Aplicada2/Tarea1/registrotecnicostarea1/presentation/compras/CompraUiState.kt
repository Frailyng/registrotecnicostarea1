package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto

data class CompraUiState(
    val compraId: Int? = null,
    val descripcion: String = "",
    val monto: Double = 0.0,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorDescripcion: String? = null,
    val errorMonto: String? = null,
    val successMessage: String? = null,
    val compras: List<CompraDto> = emptyList()

)