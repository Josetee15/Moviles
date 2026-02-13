package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var num1: EditText
    lateinit var num2: EditText
    lateinit var sumar: Button
    lateinit var restar: Button
    lateinit var dividir: Button
    lateinit var multiplicar: Button
    lateinit var plano1: TextView
    lateinit var plano2: TextView
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        num1 = this.findViewById<EditText>(R.id.num1)
        num2 = this.findViewById<EditText>(R.id.num2)
        sumar = this.findViewById<Button>(R.id.sumar)
        restar = this.findViewById<Button>(R.id.restar)
        dividir = this.findViewById<Button>(R.id.dividir)
        multiplicar = this.findViewById<Button>(R.id.multiplicar)
        plano1 = this.findViewById<TextView>(R.id.plano1)
        plano2 = this.findViewById<TextView>(R.id.plano2)
        resultado = this.findViewById<TextView>(R.id.mostrarResultado)

        sumar.setOnClickListener {
            val n1 = num1.text.toString().toDouble()
            val n2 = num2.text.toString().toDouble()
            resultado.text = (n1+n2).toString()
        }

        restar.setOnClickListener {
            val n1 = num1.text.toString().toDouble()
            val n2 = num2.text.toString().toDouble()
            resultado.text = (n1-n2).toString()
        }

        multiplicar.setOnClickListener {
            val n1 = num1.text.toString().toDouble()
            val n2 = num2.text.toString().toDouble()
            resultado.text = (n1*n2).toString()
        }

        dividir.setOnClickListener {
            val n1 = num1.text.toString().toDouble()
            val n2 = num2.text.toString().toDouble()
            resultado.text = (n1/n2).toString()
        }
    }

}