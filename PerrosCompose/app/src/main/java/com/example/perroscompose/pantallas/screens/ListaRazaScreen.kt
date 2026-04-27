package com.example.perroscompose.pantallas.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perroscompose.viewmodel.DogViewModel

@Composable
fun ListaRazasScreen(
    viewModel: DogViewModel,
    onRazaClick: (String) -> Unit
) {
    val razas by viewModel.razas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarRazas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Razas",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(razas) { raza ->
                Text(
                    text = raza,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            onRazaClick(raza)
                        }
                )
            }
        }
    }
}