package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.UiEvent
import kotlinx.coroutines.launch

@Composable
fun CompraScreen(
    compraId: Int? = null,
    viewModel: CompraViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(compraId) {
        compraId?.let {
            if (it > 0) {
                viewModel.onEvent(CompraEvent.GetCompra(it))
            }
        }
    }

    CompraBodyScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        goBack = goBack,
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompraBodyScreen(
    uiState: CompraUiState,
    onEvent: (CompraEvent) -> Unit,
    goBack: () -> Unit,
    viewModel: CompraViewModel
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    // Validaciones
    val descripcionError = uiState.descripcion.isNullOrBlank()
    val montoError = uiState.monto == null || uiState.monto <= 0.0
    val isFormValid = !descripcionError && !montoError

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> goBack()
                is UiEvent.ShowSnackbar -> TODO()
            }
        }
    }

    // Mostrar Snackbar y navegar al éxito
    LaunchedEffect(uiState.isSuccess || !uiState.errorMessage.isNullOrBlank()) {
        if (uiState.isSuccess && !uiState.successMessage.isNullOrBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.successMessage,
                    duration = SnackbarDuration.Short
                )
                onEvent(CompraEvent.ResetSuccessMessage)
                goBack() // Navegar a la lista después de mostrar el Snackbar
            }
        } else if (!uiState.errorMessage.isNullOrBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.errorMessage,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if (uiState.isSuccess) Color.Green.copy(alpha = 0.8f) else Color.Red.copy(alpha = 0.8f)
                )
            }
        },
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = goBack,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "volver")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text("Registro de compras")

                    OutlinedTextField(
                        value = uiState.compraId?.toString() ?: "Nuevo",
                        onValueChange = {},
                        label = { Text("ID Compra") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        enabled = false
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.descripcion ?: "",
                        onValueChange = { onEvent(CompraEvent.DescripcionChange(it)) },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = descripcionError,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue,
                            errorBorderColor = Color.Red
                        )
                    )
                    if (descripcionError) {
                        Text(
                            text = "La descripción no puede estar vacía",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.monto?.toString() ?: "",
                        onValueChange = { onEvent(CompraEvent.MontoChange(it.toDoubleOrNull() ?: 0.0)) },
                        label = { Text("Monto") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = montoError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue,
                            errorBorderColor = Color.Red
                        )
                    )
                    if (montoError) {
                        Text(
                            text = "El monto debe ser mayor a 0",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    uiState.errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }

                    Spacer(modifier = Modifier.padding(2.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { onEvent(CompraEvent.Nuevo) },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.Blue
                            ),
                            border = BorderStroke(1.dp, Color.Blue),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "new button"
                            )
                            Text("Nuevo")
                        }
                        OutlinedButton(
                            onClick = {
                                if (isFormValid) {
                                    onEvent(CompraEvent.PostCompra)
                                    // goBack() removido de aquí, ahora se maneja en LaunchedEffect
                                }
                            },
                            enabled = isFormValid,
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = if (isFormValid) Color.Blue else Color.Gray,
                                disabledContentColor = Color.Gray
                            ),
                            border = BorderStroke(1.dp, if (isFormValid) Color.Blue else Color.Gray),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            /* Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "save button"
                            )*/
                            Text(text = "Guardar")
                        }
                    }
                }
            }
        }
    }
}