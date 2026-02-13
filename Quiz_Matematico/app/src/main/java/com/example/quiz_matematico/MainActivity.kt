package com.example.quiz_matematico

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private var yearActual: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<LinearLayout>(R.id.root1)
        val swFondo = findViewById<Switch>(R.id.swFondo)

        val btnRandom = findViewById<Button>(R.id.btnRandomYear)
        val tvYear = findViewById<TextView>(R.id.tvYear)

        val rg = findViewById<RadioGroup>(R.id.rgBisiesto)
        val btnComprobar = findViewById<Button>(R.id.btnComprobar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        swFondo.setOnCheckedChangeListener { _, isChecked ->
            root.setBackgroundColor(if (isChecked) Color.YELLOW else Color.WHITE)
        }

        btnRandom.setOnClickListener {
            yearActual = Random.nextInt(1900, 2501)
            tvYear.text = yearActual.toString()
            rg.clearCheck()
            tvResultado.text = ""
        }

        btnComprobar.setOnClickListener {
            if (rg.checkedRadioButtonId == -1) {
                tvResultado.text = "Debe escoger una de las opciones"
                tvResultado.setTextColor(Color.BLUE)
                return@setOnClickListener
            }

            val year = yearActual ?: return@setOnClickListener
            val esBisiesto = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
            val usuarioDiceSi = (rg.checkedRadioButtonId == R.id.rbSi)

            if (usuarioDiceSi == esBisiesto) {
                tvResultado.text = "Correcto"
                tvResultado.setTextColor(Color.GREEN)
            } else {
                tvResultado.text = "Error"
                tvResultado.setTextColor(Color.RED)
            }
        }
    }
}
