package com.example.calculadoracompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculadoraComposable() {

    val miViewModel: MiViewModel = viewModel()

    var numero1 by rememberSaveable { mutableStateOf("") }
    var numero2 by rememberSaveable { mutableStateOf("") }

    val resultado by miViewModel.resultado.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .offset(y = 30.dp)
    ) {

        // COLUMN1 nums
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .background(Color.Cyan)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Número 1:",
                    modifier = Modifier.padding(end = 12.dp)
                )

                TextField(
                    value = numero1,
                    onValueChange = { numero1 = it },
                    placeholder = { Text("Introduce un número") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Número 2:",
                    modifier = Modifier.padding(end = 12.dp)
                )

                TextField(
                    value = numero2,
                    onValueChange = { numero2 = it },
                    placeholder = { Text("Introduce un número") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // ROW CENTRO btns
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.DarkGray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val n1 = numero1.toDouble()
                val n2 = numero2.toDouble()

                miViewModel.sumar(n1, n2)
            }) {
                Text("+")
            }

            Button(onClick = {
                val n1 = numero1.toDouble()
                val n2 = numero2.toDouble()

                miViewModel.restar(n1, n2)
            }) {
                Text("-")
            }

            Button(onClick = {
                val n1 = numero1.toDouble()
                val n2 = numero2.toDouble()

                miViewModel.multiplicar(n1, n2)
            }) {
                Text("*")
            }

            Button(onClick = {
                val n1 = numero1.toDouble()
                val n2 = numero2.toDouble()

                miViewModel.dividir(n1, n2)

            }) {
                Text("/")
            }
        }

        // COLUMN ABAJO resultado
        //MODIFICAR LO DEL RESULTADO QUE SALE CORTADO
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()

                .background(Color.Blue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = resultado.toString(),
                fontSize = 32.sp
            )
        }
    }
}