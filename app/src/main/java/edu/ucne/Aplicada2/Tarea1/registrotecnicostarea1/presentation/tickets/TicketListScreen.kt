package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.ui.theme.Registrotecnicostarea1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    ticketList: List<TicketEntity>,
    onEdit: (Int?) -> Unit,
    onDelete: (TicketEntity) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de tickets") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEdit(0) }) {
                Icon(Icons.Filled.Add, "Agregar nuevo")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(ticketList) { ticket ->
                    TicketRow(ticket, { onEdit(ticket.ticketId) }, { onDelete(ticket) })
                }
            }
        }
    }
}

@Composable
private fun TicketRow(
    ticket: TicketEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "ID: ${ticket.ticketId?.toString() ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Fecha: ${ticket.fecha}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Prioridad ID: ${ticket.prioridadId}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Cliente: ${ticket.cliente}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Asunto: ${ticket.asunto}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Descripción: ${ticket.descripcion}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Técnico ID: ${ticket.tecnicoId?.toString() ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Editar")
        }
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
    HorizontalDivider()
}

@Preview
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
}