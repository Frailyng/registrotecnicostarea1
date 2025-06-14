package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation

sealed class UiEvent {
    object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: String) : UiEvent()
}