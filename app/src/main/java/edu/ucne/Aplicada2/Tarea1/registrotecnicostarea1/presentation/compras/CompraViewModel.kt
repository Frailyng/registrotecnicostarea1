package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.Resource
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.ComprasRepository
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompraViewModel @Inject constructor(
    private val compraRepository: ComprasRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(CompraUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        getCompras()
    }

    fun onEvent(event: CompraEvent) {
        when (event) {
            is CompraEvent.MontoChange -> montoChange(event.monto)
            CompraEvent.GetCompras -> getCompras()
            CompraEvent.LimpiarErrorMessageMonto -> limpiarErrorMessageMonto()
            CompraEvent.LimpiarErrorMessageDescripcion -> limpiarErrorMessageDescripcion()
            is CompraEvent.DescripcionChange -> descripcionChange(event.descripcion)
            CompraEvent.Nuevo -> nuevo()
            CompraEvent.PostCompra -> addCompra()
            is CompraEvent.CompraIdChange -> compraIdChange(event.compraId)
            CompraEvent.ResetSuccessMessage -> _uiState.update { it.copy(isSuccess = false, successMessage = null) }
            is CompraEvent.GetCompra -> findCompra(event.id)
        }
    }

    private fun limpiarErrorMessageDescripcion() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(errorDescripcion = "")
            }
        }
    }

    private fun limpiarErrorMessageMonto() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(errorMonto = "")
            }
        }
    }

    private fun descripcionChange(descripcion: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(descripcion = descripcion)
            }
        }
    }

    private fun compraIdChange(id: Int){
        viewModelScope.launch {
            _uiState.update {
                it.copy(compraId = id)
            }
        }
    }

    private fun montoChange(monto: Double) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(monto = monto)
            }
        }
    }

    private fun nuevo() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    descripcion = "",
                    monto = 0.0,
                    errorDescripcion = "",
                    errorMonto = "",
                    errorMessage = "",
                )
            }
        }
    }

    private fun addCompra() {
        viewModelScope.launch {
            var error = false

            if (_uiState.value.descripcion.isNullOrBlank()) {
                _uiState.update {
                    it.copy(errorDescripcion = "Este campo es obligatorio *")
                }
                error = true
            }
            if (_uiState.value.monto <= 0) {
                _uiState.update {
                    it.copy(errorMonto = "Este campo es obligatorio y debe ser mayor que cero *")
                }
                error = true
            }
            if (error) return@launch
            try {
                compraRepository.saveCompra(_uiState.value.toEntity())

                // Actualizar estado con mensaje de éxito
                _uiState.update {
                    it.copy(
                        isSuccess = true,
                        successMessage = "Usuario guardado correctamente",
                        errorMessage = null
                    )
                }

                getCompras()
                nuevo()

                // Navegar de regreso después de un breve retraso para que se vea el mensaje
                delay(2000) // Espera 2 segundos para mostrar el mensaje
                _uiEvent.send(UiEvent.NavigateUp)
            }catch (e: retrofit2.HttpException) {
                if (e.code() == 500) {
                    // Si es un error 500, usa los datos locales y notifica
                    _uiState.update {
                        it.copy(
                            isSuccess = true,
                            successMessage = "Usuario guardado. Falló sincronización con el servidor (500).",
                            errorMessage = null
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Error en la API: ${e.code()} - ${e.message}",
                            isSuccess = false
                        )
                    }
                    return@launch // Salir si es otro error de API

                }
            }catch (e: Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Error al guardar el usuario: ${e.localizedMessage}",
                        isSuccess = false
                    )
                }
            }

            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

    fun findCompra(compraId: Int) {
        viewModelScope.launch {
            if (compraId > 0) {
                compraRepository.getCompras(compraId).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            val compra = resource.data?.firstOrNull()
                            _uiState.update {
                                it.copy(
                                    compraId = compra?.compraId,
                                    descripcion = compra?.descripcion ?: "",
                                    monto = compra?.monto ?: 0.0
                                )
                            }
                        }
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(errorMessage = resource.message)
                            }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                    }
                }
            }
        }
    }

    private fun getCompras() {
        viewModelScope.launch {
            compraRepository.getCompra().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                compras = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = result.message ?: "Error desconocido",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}


fun CompraUiState.toEntity() = CompraDto(
    compraId = compraId,
    descripcion = descripcion ?: "",
    monto = monto ?: 0.0,
)