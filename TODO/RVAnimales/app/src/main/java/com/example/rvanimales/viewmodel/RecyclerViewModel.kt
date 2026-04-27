package com.example.rvanimales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rvanimales.model.Datos
import com.example.rvanimales.model.RecyclerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecyclerViewModel : ViewModel() {

    var miModelo = RecyclerModel()
    private var _datos = MutableStateFlow<Datos>(Datos("", mutableListOf<String>()))

    val datos : StateFlow<Datos> get() = _datos

    public fun retornarLista(){
        viewModelScope.launch {
            _datos.value = miModelo.retornarLista()
        }
    }
    public fun anadirAnimal(animal : String, posicion: Int){
        viewModelScope.launch {
            _datos.value = miModelo.anadir(animal, posicion)
        }
    }

    public fun borrarAnimal(posicion: Int){
        viewModelScope.launch {
            _datos.value = miModelo.borrar(posicion)
        }
    }

}