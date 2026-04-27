package com.example.listacolores

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.listacolorescompose.ListaColoresViewModel

@Composable
fun ListaFrutas(){
    val miLista = listOf("Apple", "Banana", "Orange", "Mango")

    LazyColumn() {
        items(miLista) {
            item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable{
                        navController.navigate("Detalle color/$item")
                    }
            )
        }
    }
}
@Composable
fun ListaColores(){
    val miViewModel : ListaColoresViewModel = viewModel()
    val misColores = miViewModel.listaNombres.collectAsState().value


}

@Composable
fun detalleColor(color: String, miNavController: NavHostController){
    val miViewModel : ListaColoresViewModel = viewModel()
    miViewModel.detalleColor(color)
    val miColor = miViewModel.detalleColor.collectAsState().value
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
            Text(text)
        }
    }
}