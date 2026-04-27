package com.example.apidog.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    val retrofitBase = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breed/") //url donde estan los servicios api
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Interfaz con los metodos
    val retrofitService = retrofitBase.create(DogsAPIService::class.java)
}