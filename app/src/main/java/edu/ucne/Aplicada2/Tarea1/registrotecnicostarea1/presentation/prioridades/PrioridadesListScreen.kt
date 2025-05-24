package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades

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
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.ui.theme.Registrotecnicostarea1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrioridadListScreen(
    prioridadList: List<Prioridades>,
    onEdit: (Int?) -> Unit,
    onDelete: (Prioridades) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de prioridades") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEdit(0) }) {
                Icon(Icons.Filled.Add, "Agregar nueva")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(prioridadList) { prioridad ->
                    PrioridadRow(prioridad, { onEdit(prioridad.prioridadId) }, { onDelete(prioridad) })
                }
            }
        }
    }
}

@Composable
private fun PrioridadRow(
    prioridad: Prioridades,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(modifier = Modifier.weight(1f), text = prioridad.prioridadId.toString())
        Text(
            modifier = Modifier.weight(2f),
            text = prioridad.descripcion,
            style = MaterialTheme.typography.titleMedium
        )
        Text(modifier = Modifier.weight(2f), text = prioridad.tiempo.toString())
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
    val prioridades = listOf(
        Prioridades(
            prioridadId = 1,
            descripcion = "Bajo",
            tiempo = 1
        ),
        Prioridades(
            prioridadId = 2,
            descripcion = "Medio",
            tiempo = 3
        )
    )
    Registrotecnicostarea1Theme {
        PrioridadListScreen(
            prioridadList = prioridades,
            onEdit = {},
            onDelete = {}
        )
    }
}