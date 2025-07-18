package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.dao.CompraDao
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.CompraEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.RemoteDataSource
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.Resource
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class ComprasRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: CompraDao
) {
    fun getCompras(compraid: Int): Flow<Resource<List<CompraDto>>> = flow {
        try {
            emit(Resource.Loading())
            val compra = remoteDataSource.getCompra(compraid)
            emit(Resource.Success(compra))
        } catch (e: HttpException) {
            emit(Resource.Error("Error de internet: ${e.message()}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error desconocido: ${e.message}"))
        }
    }

    suspend fun saveCompra(compraDto: CompraDto) = remoteDataSource.saveCompra(compraDto)

    suspend fun deleteCompra(id: Int) = remoteDataSource.deleteCompra(id)

    suspend fun editCompra(compraDto: CompraDto) = remoteDataSource.updateCompra(compraDto)

    fun getCompra(): Flow<Resource<List<CompraDto>>> = flow {
        emit(Resource.Loading())

        try {
            val listaComprasFetched = remoteDataSource.getCompras()
            val listaEntity = listaComprasFetched.map { it.toEntity() }
            dao.save(listaEntity)
        } catch (e: Exception) {
        }

        val listaRetorno = dao.getAll()
        val listaDto = listaRetorno.map { it.toDto() }
        emit(Resource.Success(listaDto))
    }

    private fun CompraDto.toEntity() = CompraEntity(
        compraId = this.compraId,
        descripcion = this.descripcion ?: "",
        monto = this.monto ?: 0.0
    )

    private fun CompraEntity.toDto() = CompraDto(
        compraId = this.compraId,
        descripcion = this.descripcion ?: "",
        monto = this.monto ?: 0.0
    )
}