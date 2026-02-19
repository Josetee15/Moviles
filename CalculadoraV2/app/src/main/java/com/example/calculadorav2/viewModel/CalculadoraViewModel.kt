package com.example.calculadorav2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadorav2.model.Datos
import com.example.calculadorav2.model.CalculadoraModel
import kotlinx.coroutines.launch

class CalculadoraViewModel : ViewModel() {

    private val modelo = CalculadoraModel()

    private val _estadoObservable = MutableLiveData<Datos>(modelo.estadoActual)
    val estadoObservable: LiveData<Datos> = _estadoObservable

    fun numero(n: Int) = viewModelScope.launch {
        _estadoObservable.value = modelo.pulsarNumero(n)
    }

    fun operacion(op: String) = viewModelScope.launch {
        _estadoObservable.value = modelo.pulsarOperacion(op)
    }

    fun igual() = viewModelScope.launch {
        _estadoObservable.value = modelo.calcular()
    }

    fun clear() = viewModelScope.launch {
        _estadoObservable.value = modelo.limpiar()
    }
}
