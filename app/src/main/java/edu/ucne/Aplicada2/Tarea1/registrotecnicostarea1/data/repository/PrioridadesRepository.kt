package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.PrioridadDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.Prioridades
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrioridadesRepository @Inject constructor(
    private val dao: PrioridadDao
){
    suspend fun save(prioridad: Prioridades) = dao.save(prioridad)
    suspend fun find(id: Int): Prioridades? = dao.find(id)
    suspend fun delete(prioridad: Prioridades) = dao.delete(prioridad)

    fun getAll(): Flow<List<Prioridades>> = dao.getAll()
}