package com.example.perroscompose.data.repository

import com.example.perroscompose.data.network.RetrofitClient

class DogRepository {

    suspend fun obtenerRazas(): List<String>{
        //sacar razas de la api
        val response = RetrofitClient.api.getRazas()

        if(response.isSuccessful){
            //lista completa
            val mapa = response.body()?.message ?: emptyMap()

            //solo razas no subraza
            return mapa.keys.toList()
        } else {
            return emptyList()
        }
    }

    suspend fun obtenerImagenes(raza: String): List<String>{
        //saco las imagenes de LA RAZA en la api
        val response = RetrofitClient.api.getImagenesRaza(raza)

        if (response.isSuccessful){
            return response.body()?.message ?: emptyList()
        } else {
            return emptyList()
        }
    }

}