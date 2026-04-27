package com.example.listacolorescompose

class ListaColoresState {
    var listaColores = mutableListOf<MiColor>()
    init{
        listaColores.add(MiColor("Rojo", "#FF0000"))
        listaColores.add(MiColor("Verde", "#00FF00"))
        listaColores.add(MiColor("Azul", "#0000FF"))
        listaColores.add(MiColor("Amarillo", "#FFFF00"))
        listaColores.add(MiColor("Cian", "#00FFFF"))
        listaColores.add(MiColor("Magenta", "#FF00FF"))
        listaColores.add(MiColor("Naranja", "#FFA500"))
        listaColores.add(MiColor("Rosa", "#FFC0CB"))
        listaColores.add(MiColor("Morado", "#800080"))
        listaColores.add(MiColor("Marrón", "#8B4513"))
        listaColores.add(MiColor("Gris", "#808080"))
        listaColores.add(MiColor("Negro", "#000000"))
        listaColores.add(MiColor("Blanco", "#FFFFFF"))
        listaColores.add(MiColor("Turquesa", "#40E0D0"))
        listaColores.add(MiColor("Lavanda", "#E6E6FA"))
        listaColores.add(MiColor("Dorado", "#FFD700"))
        listaColores.add(MiColor("Coral", "#FF7F50"))
        listaColores.add(MiColor("Verde Oliva", "#808000"))
        listaColores.add(MiColor("Azul Marino", "#000080"))
        listaColores.add(MiColor("Beige", "#F5F5DC"))
    }

    suspend fun listaNombresColores(): List<String>{
        val nombresColores = mutableListOf<String>()

        for (color in listaColores){
            nombresColores.add(color.nombre)
        }
        return nombresColores
    }

    suspend fun detalleColor(nombre: String): MiColor{
        return listaColores.find { it.nombre == nombre }!!
    }
}