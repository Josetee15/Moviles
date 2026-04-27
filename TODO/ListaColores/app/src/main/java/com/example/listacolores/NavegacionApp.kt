package com.example.listacolores

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navegacion(){
    val miNavController = rememberNavController()
    NavHost(miNavController, startDestination = "Pantalla inicial"){
        composable("Pantalla inicial") {
            ListaColores()
        }
        composable("Detalle color/{codigo}"){
            var miCodigo = it.arguments?.getString("codigo") ?: ""
            detalleColor(miCodigo, miNavController)
        }
    }
}