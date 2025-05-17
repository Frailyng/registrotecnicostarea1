package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketsViewModel
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tickets.TicketScreen

@Composable
fun TicketNavHost(
    navHostController: NavHostController,
    ticketList: List<TicketEntity>,
    viewModel: TicketsViewModel,
    navController: NavController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.TicketsList
    ) {
        composable<Screen.TicketsList> {
            TicketListScreen(
                ticketList = ticketList,
                onEdit = { ticketId ->
                    navHostController.navigate(Screen.Tickets(ticketId))
                },
                onDelete = { ticket ->
                    viewModel.deleteTicket(ticket)
                }
            )
        }

        composable<Screen.Tickets> { backStack ->
            val ticketId = backStack.toRoute<Screen.Tickets>().ticketId
            TicketScreen(
                ticketId = ticketId,
                viewModel = viewModel,
                navController = navController
            ) {
            }
        }
    }
}