package com.example.apidog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidog.model.Datos
import com.example.apidog.model.MainState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val miEstado = MainState()

    private val _datos = MutableLiveData<Datos>()
    val datos: LiveData<Datos> get() = _datos

    fun devuelveFotos(raza: String) {
        viewModelScope.launch {
            _datos.value = miEstado.recuperarFotosPag(raza)
        }
    }

    fun scrollFotos() {
        _datos.value = miEstado.scrollFotos()
    }
}