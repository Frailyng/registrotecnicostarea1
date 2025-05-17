package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.ui.theme.Registrotecnicostarea1Theme
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database.TecnicoDb
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicosViewModel
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TecnicosRepository
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.TecnicosNavHost
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.TicketNavHost
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketViewModel
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TicketsRepository

class MainActivity : ComponentActivity() {
    private lateinit var tecnicoDb: TecnicoDb
    private lateinit var tecnicosRepository: TecnicosRepository
    private lateinit var ticketsRepository: TicketsRepository
    private lateinit var tecnicosViewModel: TecnicosViewModel
    private lateinit var ticketViewModel: TicketViewModel

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
        ticketViewModel = TicketViewModel(ticketsRepository)

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
                val nav = rememberNavController()
                TecnicosNavHost(
                    nav,
                    tecnicoList,
                    tecnicosViewModel,
                    nav
                )
                TicketNavHost(
                    nav,
                    ticketList,
                    ticketViewModel,
                    nav
                )
            }
        }
    }
}