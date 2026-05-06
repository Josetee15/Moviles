package com.example.mitienda_goaldistrict.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitienda_goaldistrict.model.Carro
import com.example.mitienda_goaldistrict.repository.CarroRepository
import kotlinx.coroutines.launch

class CarroViewModel : ViewModel() {

    private val repository = CarroRepository()

    val carro = MutableLiveData<Carro>()
    val error = MutableLiveData<String>()
    val productoBorrado = MutableLiveData<Boolean>()
    val productoAnadido = MutableLiveData<Boolean>()

    fun cargarCarro(token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getCarro(token)

                if (response.isSuccessful) {
                    val resultado = response.body()

                    if (resultado != null) {
                        carro.value = resultado
                    }
                } else {
                    error.value = "Error al cargar el carro"
                }

            } catch (e: Exception) {
                error.value = "Error de conexión"
            }
        }
    }

    fun addProductoCarro(token: String, productId: Long, quantity: Int) {
        viewModelScope.launch {
            try {
                val response = repository.addProductoCarro(token, productId, quantity)

                if (response.isSuccessful) {
                    // avisamos a la activity que se ha añadido el prod
                    productoAnadido.value = true
                } else {
                    error.value = "Error al añadir producto al carro"
                }

            } catch (e: Exception) {
                error.value = "Error de conexión"
            }
        }
    }

    fun deleteProductoCarro(token: String, productId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.deleteProductoCarro(token, productId)

                if (response.isSuccessful) {
                    productoBorrado.value = true
                    //actualizamos la lista
                    cargarCarro(token)
                } else {
                    error.value = "Error al borrar producto del carro"
                }

            } catch (e: Exception) {
                error.value = "Error de conexión"
            }
        }
    }
}