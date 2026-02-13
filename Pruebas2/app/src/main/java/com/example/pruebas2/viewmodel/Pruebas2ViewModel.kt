package com.example.pruebas2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebas2.model.Datos
import kotlinx.coroutines.launch

class Pruebas2ViewModel : ViewModel() {
    val miModelo = Pruebas2ViewModel()
    private val _misDatos = MutableLiveData<Datos>()
    val misDatosObservables: LiveData<Datos> get() = _misDatos

    public fun sumar(num: Int): Datos {
        viewModelScope.launch {
            _misDatos.value = miModelo.sumar(num) }

    }

    public fun restar(num : Int) : Datos {
        viewModelScope.launch {
            _misDatos.value = miModelo.restar(num) }
    }
}


