package com.example.compose

class MiEstado {
    var num : Int = 0

    fun sumar(numSumar : Int) : Int {
        num += numSumar

        return num
    }
}