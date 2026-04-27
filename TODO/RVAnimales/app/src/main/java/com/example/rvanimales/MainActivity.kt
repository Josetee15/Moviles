package com.example.rvanimales

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvanimales.databinding.ActivityMainBinding
import com.example.rvanimales.recycler.MiAdaptador
import com.example.rvanimales.viewmodel.RecyclerViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var miAdaptador: MiAdaptador

    private val miViewModel: RecyclerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        miViewModel.retornarLista()

        binding.btnBorrar.setOnClickListener {
            miViewModel.borrarAnimal(miAdaptador.posicionSeleccionada)
        }

        binding.btnBorrar.setOnClickListener {
            miViewModel.anadirAnimal(
                binding.animal.text.toString(),
                miAdaptador.posicionSeleccionada
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                miViewModel.datos.collect {
                    when (it.estado) {
                        "ok" -> {
                            miAdaptador = MiAdaptador(it)
                            binding.recyclerView.layoutManager = LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL, false
                            )
                            binding.recyclerView.adapter = miAdaptador

                            //DIVISION ENTRE LAS FILAS

                            val midividerItemDecoration =
                                DividerItemDecoration(
                                    this@MainActivity,
                                    DividerItemDecoration.VERTICAL
                                )
                            binding.recyclerView.addItemDecoration(midividerItemDecoration)
                        }

                        "error" -> Toast.makeText(this@MainActivity, "Seleccione animal a borrar",)
                        "add" -> miAdaptador.notifyDataSetChanged()
                        "del" -> miAdaptador.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}

