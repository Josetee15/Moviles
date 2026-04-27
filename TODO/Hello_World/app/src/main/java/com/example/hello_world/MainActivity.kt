package com.example.hello_world

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var miTexto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var miBoton : Button = this.findViewById<Button>(R.id.bt1)
        miTexto = this.findViewById<TextView>(R.id.tv1)
       // miBoton.setOnClickListener { miTexto.text = "algo" }
        miBoton.setOnLongClickListener { miTexto.text = "otro algo distinto"
            true }
    }
    fun ponTexto(v: View){
        miTexto.text = "lo que quieras"
        miTexto.setTextColor(Color.BLUE)
    }
}