package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.CompraDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.MensajeDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.PrioridadDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TecnicoDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.TicketDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.CompraEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.MensajeEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TicketEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

@Database(
    entities = [
        TecnicoEntity::class,
        TicketEntity::class,
        Prioridades::class,
        MensajeEntity::class,
        CompraEntity::class
    ],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TecnicoDb : RoomDatabase() {
    abstract fun TecnicoDao(): TecnicoDao
    abstract fun TicketDao(): TicketDao
    abstract fun PrioridadDao(): PrioridadDao
    abstract fun MensajeDao(): MensajeDao
    abstract fun CompraDao(): CompraDao
}