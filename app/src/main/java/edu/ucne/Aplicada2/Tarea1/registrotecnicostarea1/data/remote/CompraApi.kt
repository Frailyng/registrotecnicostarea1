package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote

import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.dto.CompraDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CompraApi {
    @GET("api/Compras")
    suspend fun getCompras(): List<CompraDto>

    @GET("api/Compras/{id}")
    suspend fun getCompras(@Path("id") id: Int): List<CompraDto>

    @PUT("api/Compras/{id}")
    suspend fun updateCompra(@Body compraDto: CompraDto): CompraDto

    @POST("api/Compras")
    suspend fun saveCompra(@Body compraDto: CompraDto): CompraDto

    @DELETE("api/Compras/{id}")
    suspend fun deleteCompra(@Path("id") id: Int): Response<Unit>

}