package com.example.calculadorav2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorav2.databinding.ActivityMainBinding
import com.example.calculadorav2.viewModel.CalculadoraViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.operaciones)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.estadoObservable.observe(this) {
            binding.resultado.text = it.numero
            binding.operacion.text = it.acumulado
            if (it.estado.isNotBlank()) {
                Toast.makeText(this, it.estado, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun configNum(v: View) {
        val n = (v as Button).text.toString().toInt()
        viewModel.numero(n)
    }

    fun configSimb(v: View) {
        val t = (v as Button).text.toString()
        when (t) {
            "Clear", "clear" -> viewModel.clear()
            "=" -> viewModel.igual()
            "+", "-", "*", "/" -> viewModel.operacion(t)
        }
    }
}
