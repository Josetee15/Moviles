package com.example.recyclerview_colores.model

class RecycledModel {

    var colores = mutableListOf(
        Datos("rojo", "#ff0000"),
        Datos("azul", "#0000ff"),
        Datos("verde", "#008000"),
        Datos("amarillo", "#ffff00"),
        Datos("morado", "#800080"),
        Datos("blanco", "#ffffff"),
        Datos("negro", "#000000"),
        Datos("marron", "#a52a2a"),
        Datos("gris", "#808080"),
        Datos("rosa", "#ffc0cb"),
        Datos("naranja", "#ffa500"),
        Datos("ocre", "#cc7722")
    )

    public suspend fun retornarLista() : Respuesta {
        return Respuesta("ok", colores)
    }
}