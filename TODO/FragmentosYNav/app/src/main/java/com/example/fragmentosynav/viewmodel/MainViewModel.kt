package com.example.fragmentosynav.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentosynav.model.EstadoRespuesta

class MainViewModel : ViewModel() {

    private val _numero = MutableLiveData<Int?>(null)
    val numero: LiveData<Int?> = _numero

    private val _resultadoBisiesto = MutableLiveData(EstadoRespuesta.PENDIENTE)
    val resultadoBisiesto: LiveData<EstadoRespuesta> = _resultadoBisiesto

    private val _resultadoDivisible = MutableLiveData(EstadoRespuesta.PENDIENTE)
    val resultadoDivisible: LiveData<EstadoRespuesta> = _resultadoDivisible

    fun generarNumero() {
        _numero.value = (1900..2200).random()
        _resultadoBisiesto.value = EstadoRespuesta.PENDIENTE
        _resultadoDivisible.value = EstadoRespuesta.PENDIENTE
    }

    fun hayNumeroGenerado(): Boolean {
        return _numero.value != null
    }

    fun validarBisiesto(respuestaUsuario: Boolean) {
        val numeroActual = _numero.value ?: return
        val esCorrecta = respuestaUsuario == esBisiesto(numeroActual)

        _resultadoBisiesto.value =
            if (esCorrecta) EstadoRespuesta.CORRECTO
            else EstadoRespuesta.INCORRECTO
    }

    fun validarDivisible(
        por2: Boolean,
        por3: Boolean,
        por5: Boolean,
        por10: Boolean,
        ninguno: Boolean
    ) {
        val numeroActual = _numero.value ?: return

        val esPor2 = numeroActual % 2 == 0
        val esPor3 = numeroActual % 3 == 0
        val esPor5 = numeroActual % 5 == 0
        val esPor10 = numeroActual % 10 == 0
        val esNinguno = !esPor2 && !esPor3 && !esPor5 && !esPor10

        val esCorrecta =
            por2 == esPor2 &&
                    por3 == esPor3 &&
                    por5 == esPor5 &&
                    por10 == esPor10 &&
                    ninguno == esNinguno

        _resultadoDivisible.value =
            if (esCorrecta) EstadoRespuesta.CORRECTO
            else EstadoRespuesta.INCORRECTO
    }

    private fun esBisiesto(anio: Int): Boolean {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)
    }
}