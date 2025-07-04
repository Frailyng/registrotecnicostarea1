package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos

 sealed interface TecnicoEvent {
     data class TecnicoChange(val tecnicoId: Int): TecnicoEvent
     data class NombreChange(val nombre: String): TecnicoEvent
     data class SueldoChange(val sueldo: Double): TecnicoEvent
     data object Save: TecnicoEvent
     data object Delete: TecnicoEvent
     data object New: TecnicoEvent
}