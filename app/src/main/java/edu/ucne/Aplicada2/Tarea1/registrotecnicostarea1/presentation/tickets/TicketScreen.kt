package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity

@Composable
fun TicketScreen(
    ticketId: Int? = null,
    viewModel: TicketsViewModel,
    navController: NavController,
    function: () -> Unit
) {
    var fecha: String by remember { mutableStateOf("") }
    var prioridadId: Int by remember { mutableStateOf(0) }
    var cliente: String by remember { mutableStateOf("") }
    var asunto: String by remember { mutableStateOf("") }
    var descripcion: String by remember { mutableStateOf("") }
    var tecnicoId: Int? by remember { mutableStateOf(null) }
    var errorMessage: String? by remember { mutableStateOf("") }
    var editando by remember { mutableStateOf<TicketEntity?>(null) }

    LaunchedEffect(ticketId) {
        if (ticketId != null && ticketId > 0) {
            val ticket = viewModel.findTicket(ticketId)
            ticket?.let {
                editando = it
                fecha = it.fecha
                prioridadId = it.prioridadId
                cliente = it.cliente
                asunto = it.asunto
                descripcion = it.descripcion
                tecnicoId = it.tecnicoId
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (navController != null) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "volver")
                    }
                }
            }
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text("Registro de tickets $ticketId")

                    OutlinedTextField(
                        value = editando?.ticketId?.toString() ?: "0",
                        onValueChange = {},
                        label = { Text("ID Ticket") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        enabled = false
                    )
                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        label = { Text("Fecha") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )
                    OutlinedTextField(
                        value = prioridadId.toString(),
                        onValueChange = { newValue ->
                            prioridadId = newValue.toIntOrNull() ?: 0
                        },
                        label = { Text("ID Prioridad") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )
                    OutlinedTextField(
                        value = cliente,
                        onValueChange = { cliente = it },
                        label = { Text("Cliente") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )
                    OutlinedTextField(
                        value = asunto,
                        onValueChange = { asunto = it },
                        label = { Text("Asunto") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )
                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )
                    OutlinedTextField(
                        value = tecnicoId?.toString() ?: "",
                        onValueChange = { newValue ->
                            tecnicoId = newValue.toIntOrNull()
                        },
                        label = { Text("ID Técnico") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue
                        )
                    )

                    Spacer(modifier = Modifier.padding(2.dp))
                    errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = {
                                fecha = ""
                                prioridadId = 0
                                cliente = ""
                                asunto = ""
                                descripcion = ""
                                tecnicoId = null
                                errorMessage = null
                                editando = null
                            },
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
                            Text(text = "Nuevo")
                        }

                        OutlinedButton(
                            onClick = {
                                if (fecha.isBlank()) {
                                    errorMessage = "La fecha no puede estar vacía."
                                    return@OutlinedButton
                                }
                                if (prioridadId <= 0) {
                                    errorMessage = "El ID de prioridad debe ser mayor que cero."
                                    return@OutlinedButton
                                }
                                if (cliente.isBlank()) {
                                    errorMessage = "El cliente no puede estar vacío."
                                    return@OutlinedButton
                                }
                                if (asunto.isBlank()) {
                                    errorMessage = "El asunto no puede estar vacío."
                                    return@OutlinedButton
                                }
                                if (descripcion.isBlank()) {
                                    errorMessage = "La descripción no puede estar vacía."
                                    return@OutlinedButton
                                }
                                val tecnicoIdValue = tecnicoId
                                if (tecnicoIdValue != null && tecnicoIdValue <= 0) {
                                    errorMessage = "El ID del técnico debe ser mayor que cero si se especifica."
                                    return@OutlinedButton
                                }

                                viewModel.saveTicket(
                                    TicketEntity(
                                        ticketId = editando?.ticketId,
                                        fecha = fecha,
                                        prioridadId = prioridadId,
                                        cliente = cliente,
                                        asunto = asunto,
                                        descripcion = descripcion,
                                        tecnicoId = tecnicoId
                                    )
                                )
                                fecha = ""
                                prioridadId = 0
                                cliente = ""
                                asunto = ""
                                descripcion = ""
                                tecnicoId = null
                                errorMessage = null
                                editando = null

                                navController.navigateUp()
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.Blue
                            ),
                            border = BorderStroke(1.dp, Color.Blue),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "save button"
                            )
                            Text(text = "Guardar")
                        }
                    }
                }
            }
        }
    }
}