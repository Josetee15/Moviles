package com.example.apidog

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apidog.model.Datos
import com.example.apidog.recycler.MiAdaptador
import com.example.apidog.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var txt1: EditText
    lateinit var btn: Button
    lateinit var rvPerros: RecyclerView
    lateinit var miAdaptador: MiAdaptador
    lateinit var mLayout: LinearLayoutManager
    lateinit var myViewModel: MainViewModel
    lateinit var misDatos: Datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt1 = findViewById(R.id.txt1)
        btn = findViewById(R.id.btn)
        rvPerros = findViewById(R.id.rvPerros)

        mLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPerros.layoutManager = mLayout

        myViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        btn.setOnClickListener {
            val raza = txt1.text.toString().trim().lowercase()

            if (raza.isNotEmpty()) {
                myViewModel.devuelveFotos(raza)
            } else {
                Toast.makeText(this, "Introduce una raza", Toast.LENGTH_SHORT).show()
            }
        }

        myViewModel.datos.observe(this) {
            when (it.status) {
                "success" -> {
                    if (it.paginaActual == 1) {
                        misDatos = it
                        miAdaptador = MiAdaptador(it.message!!)
                        rvPerros.adapter = miAdaptador
                    } else {
                        misDatos = it
                        val inicio = (it.paginaActual!! - 1) * 10
                        val cantidadInsertada = it.message!!.size - inicio
                        miAdaptador.notifyItemRangeInserted(inicio, cantidadInsertada)
                    }
                }

                "error" -> {
                    Toast.makeText(this, "No hay fotos de esa raza", Toast.LENGTH_SHORT).show()
                }
            }
        }

        rvPerros.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!::misDatos.isInitialized) return

                var finalScroll = false

                if (mLayout.findLastVisibleItemPosition() % 10 >= 9 &&
                    mLayout.findLastVisibleItemPosition() / 10 == (misDatos.paginaActual!! - 1)
                ) {
                    finalScroll = true
                }

                if (finalScroll && misDatos.paginaActual!! < misDatos.numPaginas!!) {
                    Snackbar.make(
                        findViewById(R.id.main),
                        "¿Quieres mas fotos? PULSA AQUÍ",
                        Snackbar.LENGTH_LONG
                    ).setAction("Cargar más fotos") {
                        myViewModel.scrollFotos()
                    }.show()
                }
            }
        })
    }
}