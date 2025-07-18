package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.CompraEntity

@Dao
interface CompraDao {
    @Upsert
    suspend fun save(compra: List<CompraEntity>)

    @Query(
        """
            SELECT *
            FROM Compras
            WHERE compraId=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): CompraEntity?

    @Delete
    suspend fun delete(compra: CompraEntity)

    @Query("SELECT * FROM Compras")
    suspend fun getAll(): List<CompraEntity>
}