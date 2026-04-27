package com.example.listacolorescompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaColoresViewModel: ViewModel() {

    val myState : ListaColoresState = ListaColoresState()
    private val _listaNombres = MutableStateFlow<List<String>>(emptyList())
    val listaNombres : StateFlow<List<String>> get() = _listaNombres

    fun listaNombres(){
        viewModelScope.launch {
            _listaNombres.value = myState.listaNombresColores()

        }
    }

    init{
        listaNombres()
    }
    private val _detalleColor = MutableStateFlow<MiColor>(MiColor("",""))
    val detalleColor : StateFlow<MiColor> get() = _detalleColor

    fun detalleColor(color: String){
        viewModelScope.launch {
            _detalleColor.value = myState.detalleColor(color)

        }
    }
}