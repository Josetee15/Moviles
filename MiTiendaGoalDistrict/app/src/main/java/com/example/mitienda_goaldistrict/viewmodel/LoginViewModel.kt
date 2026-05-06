package com.example.mitienda_goaldistrict.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitienda_goaldistrict.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()

    val token = MutableLiveData<String>()
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()

    fun login(username: String, password: String){
        cargando.value = true

        viewModelScope.launch {
            try {
                // el viewmodel llama al repositorio para hacer login contra la api
                val response = repository.login(username, password)

                if (response.isSuccessful){
                    val loginResponse = response.body()

                    if (loginResponse != null){
                        // si el login es correcto, guardamos el token para pasarlo a la siguiente pantalla
                        token.value = loginResponse.accessToken
                    } else {
                        error.value = "Respuesta vacía"
                    }
                } else {
                    error.value = "Usuario o contraseña incorrectos"
                }
            } catch (e: Exception){
                error.value = "Error de conexion con la API"
            }

            cargando.value = false
        }
    }
}