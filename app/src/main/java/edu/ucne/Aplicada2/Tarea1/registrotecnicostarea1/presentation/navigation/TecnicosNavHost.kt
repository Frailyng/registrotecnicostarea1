package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicoListScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicoScreen
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos.TecnicosViewModel

@Composable
fun TecnicosNavHost(
    navHostController: NavHostController,
    tecnicoList: List<TecnicoEntity>,
    viewModel: TecnicosViewModel,
    navController: NavController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.TecnicoList
    ) {
        composable<Screen.TecnicoList> {
            TecnicoListScreen(
                tecnicoList = tecnicoList,
                onEdit = { tecnicoId ->
                    navHostController.navigate(Screen.Tecnico(tecnicoId))
                },
                onDelete = { tecnico ->
                    viewModel.deleteTecnico(tecnico)
                }
            )
        }

        composable<Screen.Tecnico>{ backStack ->
            val tecnicoId  = backStack.toRoute<Screen.Tecnico>().tecnicoId
            TecnicoScreen(
                tecnicoId,
                viewModel,
                navController
            ) {
            }
        }
    }
}