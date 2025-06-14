package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val compraApi: CompraApi
){
    suspend fun getCompras()= compraApi.getCompras()

    suspend fun updateCompra(compraDto: CompraDto)= compraApi.updateCompra(compraDto)

    suspend fun saveCompra(compraDto: CompraDto)= compraApi.saveCompra(compraDto)

    suspend fun  deleteCompra(id: Int)= compraApi.deleteCompra(id)

    suspend fun getCompra(id: Int)= compraApi.getCompras(id)
}