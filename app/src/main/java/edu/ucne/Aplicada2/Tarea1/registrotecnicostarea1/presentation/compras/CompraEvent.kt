package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras

interface CompraEvent {
    data class CompraIdChange(val compraId: Int): CompraEvent
    data class DescripcionChange(val descripcion: String): CompraEvent
    data class MontoChange(val monto: Double): CompraEvent

    data object PostCompra: CompraEvent
    data object GetCompras: CompraEvent
    data object Nuevo: CompraEvent
    data object LimpiarErrorMessageDescripcion: CompraEvent
    data object LimpiarErrorMessageMonto: CompraEvent
    data class GetCompra(val id: Int): CompraEvent
    data object ResetSuccessMessage: CompraEvent
}