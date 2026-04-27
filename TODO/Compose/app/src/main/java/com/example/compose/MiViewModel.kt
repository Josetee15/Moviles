package com.example.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MiViewModel : ViewModel() {
    private val miEstado = MiEstado()
    private val _cont = MutableStateFlow<Int>(0)

    val cont : StateFlow<Int> get() = _cont

    fun sumar(numSumar : Int) {
        _cont.value = miEstado.sumar(numSumar)
    }
}