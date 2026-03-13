package com.example.apidog.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainState {

    val retrofitApi = RetrofitApi()
    lateinit var fotosPerrosCargado: DogRespuesta
    lateinit var misDatos: Datos

    suspend fun recuperarFotosPag(raza: String):
            Datos = withContext(Dispatchers.IO) {

        val respuesta = retrofitApi.retrofitService.getFotosPerros(raza)

        if (respuesta.isSuccessful) {
            fotosPerrosCargado = DogRespuesta(
                respuesta.body()!!.status,
                respuesta.body()!!.message
            )

            if (fotosPerrosCargado.message != null && fotosPerrosCargado.message!!.isNotEmpty()) {
                var numPaginas: Int = fotosPerrosCargado.message!!.size / 10

                if (fotosPerrosCargado.message!!.size % 10 != 0) {
                    numPaginas++
                }

                misDatos = Datos(
                    fotosPerrosCargado.status,
                    numPaginas,
                    1,
                    mutableListOf()
                )

                val rango = Math.min(fotosPerrosCargado.message!!.size - 1, 9)

                for (i in 0..rango) {
                    misDatos.message!!.add(fotosPerrosCargado.message!![i])
                }

                misDatos
            } else {
                Datos("error", null, null, null)
            }
        } else {
            Datos("error", null, null, null)
        }
    }

    fun scrollFotos(): Datos {
        var inicio: Int
        var fin: Int

        inicio = misDatos.paginaActual!! * 10
        misDatos.paginaActual = misDatos.paginaActual!! + 1

        if (misDatos.paginaActual!! < misDatos.numPaginas!!) {
            fin = (misDatos.paginaActual!! * 10) - 1
        } else {
            fin = fotosPerrosCargado.message!!.size - 1
        }

        for (i in inicio..fin) {
            misDatos.message!!.add(fotosPerrosCargado.message!![i])
        }

        return misDatos;
    }
}