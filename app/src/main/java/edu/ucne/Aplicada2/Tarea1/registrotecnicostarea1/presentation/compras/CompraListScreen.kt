package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto

@Composable
fun CompraListScreen(
    viewModel: CompraViewModel = hiltViewModel(),
    createCompra: () -> Unit,
    goToCompra: (Int) -> Unit,
    goBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompraListBodyScreen(
        uiState = uiState,
        goToCompra = { id -> goToCompra(id) },
        onEvent = viewModel::onEvent,
        createCompra = createCompra,
        goBack = goBack
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CompraListBodyScreen(
    uiState: CompraUiState,
    goToCompra: (Int) -> Unit,
    onEvent: (CompraEvent) -> Unit,
    createCompra: () -> Unit,
    goBack: () -> Unit
) {
    val refreshing = uiState.isLoading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = { onEvent(CompraEvent.GetCompras) }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = " API Compras ",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF272D4D)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = createCompra) {
                Icon(Icons.Filled.Add, "Agregar nueva")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                if (uiState.compras.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay compras registradas",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        items(uiState.compras) { compra ->
                            ComprasRow(
                                it = compra,
                                goToCompra = { goToCompra(compra.compraId ?: 0) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                if (!uiState.errorMessage.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = uiState.errorMessage,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun ComprasRow(
    it: CompraDto,
    goToCompra: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(modifier = Modifier.weight(1f), text = it.compraId.toString(), color = Color.Black)
        Text(
            modifier = Modifier.weight(2f),
            text = it.descripcion,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(modifier = Modifier.weight(2f), text = it.monto.toString(), color = Color.Black)
//        IconButton(onClick = goToCompras) {
//            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
//        }
    }
    HorizontalDivider()
}