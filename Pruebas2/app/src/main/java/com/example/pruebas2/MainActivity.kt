package com.example.pruebas2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas2.databinding.ActivityMainBinding
import com.example.pruebas2.model.Datos
import com.example.pruebas2.viewmodel.Pruebas2ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val miViewModel : Pruebas2ViewModel by viewModels()
    var misDatos = Datos("ok", 0,0, false)

//    var cont : Int = 0
//    var clicks : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btn1.setOnClickListener {
            miViewModel.sumar(1)
//            misDatos = miViewModel.sumar(1)
//            binding.text1.text = misDatos.contador.toString()
//            if (misDatos.mostrarToast){
//                Toast.makeText(this, "Hemos llegado a 5 clicks", Toast.LENGTH_LONG).show()
//            }
        }

        binding.btn1.setOnClickListener {
            miViewModel.restar(1)

//            misDatos = miViewModel.restar(1)
//            binding.text1.text = misDatos.contador.toString()
//            if (misDatos.mostrarToast){
//                Toast.makeText(this, "Hemos llegado a 5 clicks", Toast.LENGTH_LONG).show()
//            }
        }

        miViewModel.misDatosObservables.observe(this,{
            misDatosRecibidos ->
            binding.text1.text = misDatosRecibidos.contador.toString()
            if (misDatosRecibidos.mostrarToast){
                Toast.makeText(this, "Hemos llegado a 5 clicks", Toast.LENGTH_LONG).show()
            }
        })
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("Valor de contador", cont)
//        outState.putInt("Valor de los clicks", clicks)
//
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        cont = savedInstanceState.getInt("Valor de contador")
//        clicks = savedInstanceState.getInt("Valor de clicks")
//        binding.text1.text = cont.toString()
//
//    }
}