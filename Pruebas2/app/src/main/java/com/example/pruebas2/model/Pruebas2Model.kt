package com.example.pruebas2.model

import kotlinx.coroutines.delay

class Pruebas2Model {

    var misDatos = Datos("ok", 0, 0, false)

    public suspend fun sumar(num : Int) : Datos {
        delay(2000)
        misDatos.contador += num;
        misDatos.clicks += num;

        if (misDatos.clicks % 5 == 0){
            misDatos.mostrarToast = true;
        }
        return misDatos;
    }

    public suspend fun restar(num : Int) : Datos {
        delay(1000)
        misDatos.contador -= num;
        misDatos.clicks += num;

        if (misDatos.clicks % 5 == 0){
            misDatos.mostrarToast = true;
        }
        return misDatos;
    }
}