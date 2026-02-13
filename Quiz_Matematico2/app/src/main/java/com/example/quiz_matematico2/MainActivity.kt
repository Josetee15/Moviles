package com.example.quiz_matematico2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private var numActual: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRandom = findViewById<Button>(R.id.btnRandomNum)
        val tvNum = findViewById<TextView>(R.id.tvNum)

        val cb2 = findViewById<CheckBox>(R.id.cb2)
        val cb3 = findViewById<CheckBox>(R.id.cb3)
        val cb5 = findViewById<CheckBox>(R.id.cb5)
        val cb10 = findViewById<CheckBox>(R.id.cb10)
        val cbNinguno = findViewById<CheckBox>(R.id.cbNinguno)

        val btnComprobar = findViewById<Button>(R.id.btnComprobar2)
        val tvResultado = findViewById<TextView>(R.id.tvResultado2)

        fun limpiarChecks() {
            cb2.isChecked = false
            cb3.isChecked = false
            cb5.isChecked = false
            cb10.isChecked = false
            cbNinguno.isChecked = false
        }

        btnRandom.setOnClickListener {
            numActual = Random.nextInt(1000, 2001)
            tvNum.text = numActual.toString()
            limpiarChecks()
            tvResultado.text = ""
        }

        btnComprobar.setOnClickListener {
            val n = numActual ?: return@setOnClickListener

            val algoMarcado =
                cb2.isChecked || cb3.isChecked || cb5.isChecked || cb10.isChecked || cbNinguno.isChecked

            if (!algoMarcado) {
                tvResultado.text = "Debe escoger al menos una de las opciones"
                tvResultado.setTextColor(Color.DKGRAY)
                return@setOnClickListener
            }

            val es2 = (n % 2 == 0)
            val es3 = (n % 3 == 0)
            val es5 = (n % 5 == 0)
            val es10 = (n % 10 == 0)
            val ninguno = !(es2 || es3 || es5 || es10)

            val correcto =
                (cb2.isChecked == es2) &&
                        (cb3.isChecked == es3) &&
                        (cb5.isChecked == es5) &&
                        (cb10.isChecked == es10) &&
                        (cbNinguno.isChecked == ninguno)

            if (correcto) {
                tvResultado.text = "Correcto"
                tvResultado.setTextColor(Color.GREEN)
            } else {
                tvResultado.text = "Error"
                tvResultado.setTextColor(Color.RED)
            }
        }
    }
}
