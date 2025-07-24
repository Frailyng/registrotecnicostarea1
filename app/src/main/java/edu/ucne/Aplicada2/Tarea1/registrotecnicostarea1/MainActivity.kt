package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database.TecnicoDb
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.HomeNavHost
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.ui.theme.Registrotecnicostarea1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var tecnicoDb: TecnicoDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        tecnicoDb = Room.databaseBuilder(
            applicationContext,
            TecnicoDb::class.java,
            "Tecnico.db"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            Registrotecnicostarea1Theme {
                val nav = rememberNavController()

                HomeNavHost(
                    navHostController = nav,
                    context = this
                )
                }
            }
        }
    }