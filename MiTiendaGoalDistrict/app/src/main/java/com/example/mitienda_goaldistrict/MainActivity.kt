package com.example.mitienda_goaldistrict

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mitienda_goaldistrict.databinding.ActivityMainBinding
import com.example.mitienda_goaldistrict.fragments.CarroFragment
import com.example.mitienda_goaldistrict.fragments.HomeFragment
import com.example.mitienda_goaldistrict.fragments.ProductosFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var token: String = ""
    private var usuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recogemos el token y el usuario que vienen desde el login
        token = intent.getStringExtra("TOKEN") ?: ""
        usuario = intent.getStringExtra("USUARIO") ?: ""

        // configuramos la toolbar como barra superior de la activity
        setSupportActionBar(binding.toolbarPrincipal)
        supportActionBar?.title = usuario

        configurarTabs()

        // por defecto cargamos el fragment de home
        cargarFragment(HomeFragment())
    }

    private fun configurarTabs() {
        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setText("Home")
                .setIcon(android.R.drawable.ic_menu_view)
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setText("Productos")
                .setIcon(android.R.drawable.ic_menu_sort_by_size)
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab()
                .setText("Mi Carro")
                .setIcon(android.R.drawable.ic_menu_agenda)
        )

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> cargarFragment(HomeFragment())
                    1 -> cargarFragment(ProductosFragment.newInstance(token))
                    2 -> cargarFragment(CarroFragment.newInstance(token))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun cargarFragment(fragment: androidx.fragment.app.Fragment) {
        // cambiamos el fragment que se muestra dentro del contenedor
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragments, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // cargamos el menú de la toolbar
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemSalir -> {
                // al salir volvemos al login y cerramos esta pantalla
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}