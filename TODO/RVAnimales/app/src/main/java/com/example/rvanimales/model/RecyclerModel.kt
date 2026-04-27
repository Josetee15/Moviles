package com.example.rvanimales.model

import androidx.recyclerview.widget.RecyclerView

class RecyclerModel {
    var animales = mutableListOf("caballo", "tigre", "cabra", "gato", "perro", "leon", "ardilla", "gorrion", "elefante", "mosca", "gusano", "vaca", "burro", "mosquito")

    public suspend fun retornarLista() : Datos {
        return Datos("ok", animales)
    }

    public suspend fun anadir(animal : String, posicion: Int) : Datos{
        var miPosicion = animales.size
        if(posicion != RecyclerView.NO_POSITION){
            miPosicion = posicion
        }
        animales.add(miPosicion, animal)

        return Datos("add", animales)
    }

    suspend fun borrar(posicion: Int): Datos {
        if(posicion == RecyclerView.NO_POSITION){
            return Datos("error", animales)
        } else{
            animales.removeAt(posicion)
            return Datos("del", animales)
        }
    }
}