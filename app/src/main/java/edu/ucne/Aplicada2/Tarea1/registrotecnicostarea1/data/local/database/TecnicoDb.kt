package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.PrioridadDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TecnicoDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TicketDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades

@Database(
    entities = [
        TecnicoEntity::class,
        TicketEntity::class,
        Prioridades::class
    ],
    version = 4,
    exportSchema = false
)
abstract class TecnicoDb : RoomDatabase() {
    abstract fun TecnicoDao(): TecnicoDao
    abstract fun TicketDao(): TicketDao
    abstract fun PrioridadDao(): PrioridadDao
}