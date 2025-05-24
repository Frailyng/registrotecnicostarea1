package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import kotlinx.coroutines.flow.Flow

@Dao
interface PrioridadDao {

    @Upsert
    suspend fun save(prioridad: Prioridades)

    @Query(
        """
            SELECT *
            FROM Prioridades
            WHERE prioridadId=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): Prioridades?

    @Delete
    suspend fun delete(prioridad: Prioridades)

    @Query("SELECT * FROM Prioridades")
    fun getAll(): Flow<List<Prioridades>>

}