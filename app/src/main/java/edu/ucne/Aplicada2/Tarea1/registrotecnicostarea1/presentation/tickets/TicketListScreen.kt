package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TicketListScreen(
    viewModel: TicketsViewModel = hiltViewModel(),
    goToTicket: (Int) -> Unit,
    createTicket: () -> Unit,
    deleteTicket: ((TicketEntity) -> Unit)? = null,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TicketListBodyScreen(
        uiState = uiState,
        goToTicket = goToTicket,
        createTicket = createTicket,
        deleteTicket = { ticket ->
            viewModel.onEvent(TicketEvent.TicketChange(ticket.ticketId ?: 0))
            viewModel.onEvent(TicketEvent.Delete)
        }
    )
}

@Composable
private fun TicketRow(
    it: TicketEntity,
    goToTicket: (Int) -> Unit,
    createTicket: () -> Unit,
    deleteTicket: (TicketEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // ID
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "ID: ${it.ticketId?.toString() ?: "N/A"}",
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )

        // Fecha
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Fecha: ${it.fecha.toFormattedString()}",
            color = Color.Black
        )

        // Prioridad
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Prioridad: ${it.prioridadId?.toString() ?: "N/A"}",
            color = Color.Black
        )

        // Cliente
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Cliente: ${it.cliente}",
            color = Color.Black
        )

        // Asunto
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Asunto: ${it.asunto}",
            color = Color.Black
        )

        // Técnico
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Técnico: ${it.tecnicoId?.toString() ?: "N/A"}",
            color = Color.Black
        )

        // Descripción
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Descripción: ${it.descripcion}",
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { goToTicket(it.ticketId ?: 0) }) {
                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = { deleteTicket(it) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
    HorizontalDivider()
}

fun Date.toFormattedString(): String {
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return format.format(this)
}

@Composable
fun TicketListBodyScreen(
    uiState: TicketUiState,
    goToTicket: (Int) -> Unit,
    createTicket: () -> Unit,
    deleteTicket: (TicketEntity) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = createTicket) {
                Icon(Icons.Filled.Add, "Agregar nueva")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Lista de tickets")
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(uiState.tickets) { ticket ->
                    TicketRow(
                        it = ticket,
                        goToTicket = goToTicket,
                        createTicket = { goToTicket(ticket.ticketId ?: 0) },
                        deleteTicket = deleteTicket
                    )
                }
            }
        }
    }
}

/*@Preview
@Composable
private fun Preview() {
    val tickets = listOf(
        TicketEntity(
            ticketId = 1,
            fecha = "2025-05-16",
            prioridadId = 1,
            cliente = "Mario",
            asunto = "Problema con servidor",
            descripcion = "El servidor no responde.",
            tecnicoId = 1
        ),
        TicketEntity(
            ticketId = 2,
            fecha = "2025-05-17",
            prioridadId = 2,
            cliente = "Lucas",
            asunto = "Error en aplicación",
            descripcion = "La app se cierra inesperadamente.",
            tecnicoId = null
        )
    )
    Registrotecnicostarea1Theme {
        TicketListScreen(
            ticketList = tickets,
            onEdit = {},
            onDelete = {}
        )
    }
}*/