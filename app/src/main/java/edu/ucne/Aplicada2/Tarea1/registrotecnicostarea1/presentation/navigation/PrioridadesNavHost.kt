package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades.PrioridadListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades.PrioridadesScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.prioridades.PrioridadesViewModel

@Composable
fun PrioridadesNavHost(
    navHostController: NavHostController,
    prioridadList: List<Prioridades>,
    viewModel: PrioridadesViewModel,
    navController: NavController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PrioridadList
    ) {
        composable<Screen.PrioridadList> {
            PrioridadListScreen(
                prioridadList = prioridadList,
                onEdit = { prioridadId ->
                    navHostController.navigate(Screen.Prioridad(prioridadId))
                },
                onDelete = { prioridad ->
                    viewModel.deletePrioridad(prioridad)
                }
            )
        }

        composable<Screen.Prioridad>{ backStack ->
            val prioridadId  = backStack.toRoute<Screen.Prioridad>().prioridadId
            PrioridadesScreen(
                prioridadId,
                viewModel,
                navController
            ) {
            }
        }
    }
}