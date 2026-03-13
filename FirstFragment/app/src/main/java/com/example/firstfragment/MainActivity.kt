package com.example.firstfragment

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var btn2 : Button
    private lateinit var miFrame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn2 = findViewById<Button>(R.id.btn2)
        miFrame = findViewById<FrameLayout>(R.id.miFrame)

        btn2.setOnClickListener {
            var miFragmentoManager : FragmentManager = supportFragmentManager
            var miFragmentoTransiccion : FragmentTransaction = miFragmentoManager.beginTransaction()
            var miFragmentoExe = miFragmento()
            miFragmentoManager.replace(R.id.miFrame, miFragmentoExe).commit()
        }
    }
}