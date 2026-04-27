package com.example.perroscompose.pantallas.screens

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
import coil.compose.AsyncImage
import com.example.perroscompose.viewmodel.DogViewModel

@Composable
fun FotosScreen(
    viewModel: DogViewModel,
    raza: String
) {
    val imagenes by viewModel.imagenes.collectAsState()

    LaunchedEffect(raza) {
        viewModel.cargarImagenes(raza)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Fotos de $raza",
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(imagenes) { imagen ->
                AsyncImage(
                    model = imagen,
                    contentDescription = "Foto de $raza",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}