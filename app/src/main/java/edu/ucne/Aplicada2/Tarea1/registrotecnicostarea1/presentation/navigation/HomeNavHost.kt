package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.Home.HomeScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.mensajes.MensajeScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades.PrioridadListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades.PrioridadesScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicoListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicoScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras.CompraListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.compras.CompraScreen

@Composable
fun HomeNavHost(
    navHostController: NavHostController,
    context: Context
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home
    ) {
        //inicio
        composable <Screen.Home> {
            HomeScreen(navController = navHostController,
                context = context)
        }

        composable <Screen.PrioridadList> {

            PrioridadListScreen(
                goToPrioridad = { id ->
                    navHostController.navigate(Screen.Prioridad(id ?: 0))
                },
                createPrioridad = {
                    navHostController.navigate(Screen.Prioridad(0))
                }
            )

        }

        composable <Screen.Prioridad> { backStack ->
            val prioridadId = backStack.toRoute<Screen.Prioridad>().prioridadId
            PrioridadesScreen(
                prioridadId = prioridadId,
                goBack = { navHostController.popBackStack() }
            )
        }

        composable<Screen.TecnicoList>{

            TecnicoListScreen(
                goToTecnico = { id ->
                    navHostController.navigate(Screen.Tecnico(id ?: 0))
                },
                createTecnico = {
                    navHostController.navigate((Screen.Tecnico(0)))
                }
            )
        }

        composable <Screen.Tecnico>{ backStack ->
            val tecnicoId = backStack.toRoute<Screen.Tecnico>().tecnicoId
            TecnicoScreen(
                tecnicoId = tecnicoId,
                goBack = { navHostController.popBackStack() }
            )
        }

        composable <Screen.TicketsList>{

            TicketListScreen(
                goToTicket = { id ->
                    navHostController.navigate(Screen.Tickets(id ?: 0))
                },
                goToMensaje = { ticketId ->
                    require(ticketId != null) { "Ticket ID no puede ser null" }
                    navHostController.navigate(Screen.Mensaje(ticketId))
                },
                createTicket = {
                    navHostController.navigate((Screen.Tickets(0)))
                }
            )
        }

        composable <Screen.Tickets>{ backStack ->
            val ticketId = backStack.toRoute<Screen.Tickets>().ticketId
            TicketScreen(
                ticketId = ticketId,
                goBack = { navHostController.popBackStack()}
            )
        }

        composable<Screen.Mensaje> { backStack ->
            val ticketId = backStack.toRoute<Screen.Mensaje>().ticketId
            require(ticketId != null) { "Ticket ID no puede ser null para MensajeScreen" }
            MensajeScreen(
                ticketId = ticketId,
                goBack = { navHostController.popBackStack() }
            )
        }

        composable<Screen.CompraList> {
            CompraListScreen (
                goToCompra = { id ->
                    navHostController.navigate(Screen.Compra(id))
                },
                createCompra = {
                    navHostController.navigate(Screen.Compra(null))
                },
                goBack = { navHostController.popBackStack() }
            )
        }

        composable <Screen.Compra>{ backStack ->
            CompraScreen(
                goBack = { navHostController.popBackStack() }
            )
        }
    }
}