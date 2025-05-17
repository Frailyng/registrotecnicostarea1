package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.ui.theme.Registrotecnicostarea1Theme
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database.TecnicoDb
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TecnicosRepository
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TicketsRepository
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.Screen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.TecnicosNavHost
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicosViewModel
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.TicketNavHost
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketsViewModel

class MainActivity : ComponentActivity() {
    private lateinit var tecnicoDb: TecnicoDb
    private lateinit var tecnicosRepository: TecnicosRepository
    private lateinit var ticketsRepository: TicketsRepository
    private lateinit var tecnicosViewModel: TecnicosViewModel
    private lateinit var ticketViewModel: TicketsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        tecnicoDb = Room.databaseBuilder(
            applicationContext,
            TecnicoDb::class.java,
            "Tecnico.db"
        ).fallbackToDestructiveMigration()
            .build()

        tecnicosRepository = TecnicosRepository(tecnicoDb.TecnicoDao())
        ticketsRepository = TicketsRepository(tecnicoDb.TicketDao())

        tecnicosViewModel = TecnicosViewModel(tecnicosRepository)
        ticketViewModel = TicketsViewModel(ticketsRepository)

        setContent {
            val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
            val tecnicoList by tecnicoDb.TecnicoDao().getAll()
                .collectAsStateWithLifecycle(
                    initialValue = emptyList(),
                    lifecycleOwner = lifecycleOwner,
                    minActiveState = Lifecycle.State.STARTED
                )
            val ticketList by tecnicoDb.TicketDao().getAll()
                .collectAsStateWithLifecycle(
                    initialValue = emptyList(),
                    lifecycleOwner = lifecycleOwner,
                    minActiveState = Lifecycle.State.STARTED
                )
            Registrotecnicostarea1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            onNavigateToTecnicos = { navController.navigate(Screen.TecnicoList) },
                            onNavigateToTickets = { navController.navigate(Screen.TicketsList) }
                        )
                    }
                    composable<Screen.TecnicoList> {
                        val tecnicoNavController = rememberNavController()
                        TecnicosNavHost(
                            navHostController = tecnicoNavController,
                            tecnicoList = tecnicoList,
                            viewModel = tecnicosViewModel,
                            navController = navController
                        )
                    }
                    composable<Screen.Tecnico> { backStack ->
                        val tecnicoNavController = rememberNavController()
                        val tecnicoId = backStack.toRoute<Screen.Tecnico>().tecnicoId
                        TecnicosNavHost(
                            navHostController = tecnicoNavController,
                            tecnicoList = tecnicoList,
                            viewModel = tecnicosViewModel,
                            navController = navController
                        )
                    }
                    composable<Screen.TicketsList> {
                        val ticketNavController = rememberNavController()
                        TicketNavHost(
                            navHostController = ticketNavController,
                            ticketList = ticketList,
                            viewModel = ticketViewModel,
                            navController = navController
                        )
                    }
                    composable<Screen.Tickets> { backStack ->
                        val ticketNavController = rememberNavController()
                        val ticketId = backStack.toRoute<Screen.Tickets>().ticketId
                        TicketNavHost(
                            navHostController = ticketNavController,
                            ticketList = ticketList,
                            viewModel = ticketViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateToTecnicos: () -> Unit,
    onNavigateToTickets: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToTecnicos) {
            Text("Ver Técnicos")
        }
        Button(
            onClick = onNavigateToTickets,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Ver Tickets")
        }
    }
}