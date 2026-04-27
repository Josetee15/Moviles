package com.example.fragmentosynav

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragmentosynav.databinding.ActivityMainBinding
import com.example.fragmentosynav.fragmentos.BisiestoFrag
import com.example.fragmentosynav.fragmentos.DivisibleFrag
import com.example.fragmentosynav.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var bloqueandoCambioTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Elige número!!!"

        configurarObservadores()
        configurarBotonGenerar()
        configurarTabs()

        binding.tvNumero.text = ""
    }

    private fun configurarObservadores() {
        viewModel.numero.observe(this) { numero ->
            binding.tvNumero.text = numero?.toString() ?: ""
        }
    }

    private fun configurarBotonGenerar() {
        binding.btnGenerarNumero.setOnClickListener {
            viewModel.generarNumero()
            cargarFragmento(binding.tabLayout.selectedTabPosition)
        }
    }

    private fun configurarTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab == null || bloqueandoCambioTab) return

                if (!viewModel.hayNumeroGenerado()) {
                    bloqueandoCambioTab = true

                    Toast.makeText(
                        this@MainActivity,
                        "Primero tienes que generar un número!!!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val tabAnterior = binding.tabLayout.getTabAt(0)
                    tabAnterior?.select()

                    bloqueandoCambioTab = false
                    return
                }

                cargarFragmento(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun cargarFragmento(posicion: Int) {
        val fragment: Fragment
        val titulo: String

        if (posicion == 0) {
            fragment = BisiestoFrag()
            titulo = "Bisiesto"
        } else {
            fragment = DivisibleFrag()
            titulo = "Divisible"
        }

        supportActionBar?.title = titulo

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opcion_salir -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}