package com.example.mitienda_goaldistrict.api

import com.example.mitienda_goaldistrict.model.AddCarroRequest
import com.example.mitienda_goaldistrict.model.Carro
import com.example.mitienda_goaldistrict.model.Categoria
import com.example.mitienda_goaldistrict.model.LoginRequest
import com.example.mitienda_goaldistrict.model.LoginResponse
import com.example.mitienda_goaldistrict.model.ProductoPaginado
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("categories")
    suspend fun getCategorias(
        @Header("Authorization") token: String
    ): Response<List<Categoria>>

    @GET("products/paged")
    suspend fun getProductosPaginados(
        @Header("Authorization") token: String,
        @Query("p") pagina: Int,
        @Query("ps") tamanoPagina: Int
    ): Response<ProductoPaginado>

    @GET("categories/{categoryId}/products/paged")
    suspend fun getProductosPorCategoriaPaginados(
        @Header("Authorization") token: String,
        @Path("categoryId") categoryId: Long,
        @Query("p") pagina: Int,
        @Query("ps") tamanoPagina: Int
    ): Response<ProductoPaginado>

    @POST("cart")
    suspend fun addProductoCarro(
        @Header("Authorization") token: String,
        @Body addCarroRequest: AddCarroRequest
    ): Response<Void>

    @GET("cart")
    suspend fun getCarro(
        @Header("Authorization") token: String
    ): Response<Carro>

    @DELETE("cart/{productId}")
    suspend fun deleteProductoCarro(
        @Header("Authorization") token: String,
        @Path("productId") productId: Long
    ): Response<Void>
}