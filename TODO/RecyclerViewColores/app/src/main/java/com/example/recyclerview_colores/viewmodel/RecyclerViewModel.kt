package com.example.recyclerview_colores.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview_colores.model.RecycledModel
import com.example.recyclerview_colores.model.Respuesta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecyclerViewModel : ViewModel(){

    var miModelo = RecycledModel()

    private var _datos = MutableStateFlow<Respuesta>(Respuesta("", emptyList()))

    val datos : StateFlow<Respuesta> get() = _datos

    public fun retornarLista(){
        viewModelScope.launch {
            _datos.value = miModelo.retornarLista()
        }
    }
}