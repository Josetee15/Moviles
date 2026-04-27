package com.example.perroscompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perroscompose.data.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel : ViewModel(){

    private val repository = DogRepository() //trae datos


    private val _razas = MutableStateFlow<List<String>>(emptyList())
    //razas que leeran las pantallas
    val razas: StateFlow<List<String>> = _razas

    private val _imagenes = MutableStateFlow<List<String>>(emptyList())
    //imagenes que leeran las pantallas
    val imagenes: StateFlow<List<String>> = _imagenes

    fun cargarRazas(){
        viewModelScope.launch {
            //llamamos al repository y guardamos las razas recibidas
            _razas.value = repository.obtenerRazas()
        }
    }

    fun cargarImagenes(raza: String){
        viewModelScope.launch {
            //llamamos al repository y guardamos las imagenes recibidas
            _imagenes.value = repository.obtenerImagenes(raza)
        }
    }

}