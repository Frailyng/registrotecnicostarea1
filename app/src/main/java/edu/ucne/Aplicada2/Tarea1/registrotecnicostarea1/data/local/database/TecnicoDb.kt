package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TecnicoDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TicketDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity

@Database(
    entities = [
        TecnicoEntity::class,
        TicketEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class TecnicoDb : RoomDatabase() {
    abstract fun TecnicoDao(): TecnicoDao
    abstract fun TicketDao(): TicketDao
}