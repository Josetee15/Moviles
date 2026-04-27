package com.example.perroscompose.data.network

import com.example.perroscompose.data.model.ImagenesResponse
import com.example.perroscompose.data.model.RazasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getRazas(): Response<RazasResponse>

    @GET("breed/{raza}/images")
    suspend fun getImagenesRaza(
        @Path("raza") raza: String
    ): Response<ImagenesResponse>
}