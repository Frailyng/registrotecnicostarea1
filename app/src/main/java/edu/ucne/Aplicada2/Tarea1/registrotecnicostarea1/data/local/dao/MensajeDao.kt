package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.MensajeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MensajeDao {
    @Upsert()
    suspend fun save(mensaje: MensajeEntity)
    @Query(
        """
        SELECT * 
        FROM Mensajes 
        WHERE mensajeId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): MensajeEntity?
    @Delete
    suspend fun delete(mensaje: MensajeEntity)
    @Query("SELECT * FROM Mensajes WHERE ticketId=:ticketId")
    fun getAll(ticketId: Int): Flow<List<MensajeEntity>>
}