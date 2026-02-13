package com.example.jugandoalosdados

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var saldoNum: TextView
    private lateinit var etApuesta: EditText
    private lateinit var btnLanzar: Button
    private lateinit var btnGroup: MaterialButtonToggleGroup
    private lateinit var spOpciones: Spinner
    private lateinit var dados: ImageView
    private lateinit var tvResultado: TextView
    private lateinit var imgResultado: ImageView

    private var saldo = 100
    private var modo = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saldoNum = findViewById(R.id.saldoNum)
        etApuesta = findViewById(R.id.etApuesta)
        btnLanzar = findViewById(R.id.btnLanzar)
        btnGroup = findViewById(R.id.btnGroup)
        spOpciones = findViewById(R.id.spOpciones)
        dados = findViewById(R.id.dados)
        tvResultado = findViewById(R.id.tvResultado)
        imgResultado = findViewById(R.id.imgResultado)

        saldoNum.text = saldo.toString()

        btnGroup.check(R.id.btnUnoParImpar)
        modo = 1
        ponerOpcionesSpinner()

        btnGroup.addOnButtonCheckedListener { grupo, idBoton, marcado ->
            if (marcado) {
                if (idBoton == R.id.btnUnoParImpar) {
                    modo = 1
                } else {
                    modo = 2
                }
                ponerOpcionesSpinner()
            }
        }

        btnLanzar.setOnClickListener { tirar() }
    }

    private fun ponerOpcionesSpinner() {
        val opciones = if (modo == 1) {
            listOf("PAR", "IMPAR")
        } else {
            listOf("MAYOR O IGUAL QUE 7", "MENOR QUE 7")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spOpciones.adapter = adapter
        spOpciones.setSelection(0)
    }

    private fun tirar() {
        val textoApuesta = etApuesta.text.toString()
        val apuesta: Int

        if (textoApuesta == "") {
            apuesta = 0
        } else {
            val tmp = textoApuesta.toIntOrNull()
            if (tmp == null) {
                apuesta = 0
            } else {
                apuesta = tmp
            }
        }

        if (apuesta > saldo) {
            Toast.makeText(this, "La apuesta supera el saldo!!!", Toast.LENGTH_SHORT).show()
            return
        }

        btnLanzar.isEnabled = false
        imgResultado.visibility = View.INVISIBLE
        tvResultado.visibility = View.INVISIBLE


        dados.visibility = View.VISIBLE
        Glide.with(this)
            .asGif()
            .load(R.drawable.dado_imagen_animada_0092)
            .into(dados)

        dados.postDelayed({

            Glide.with(this).clear(dados)
            dados.setImageResource(R.drawable.dado_imagen_animada_0092)

            val d1 = Random.nextInt(1, 7)
            val d2 = Random.nextInt(1, 7)
            val suma = d1 + d2

            tvResultado.visibility = View.VISIBLE
            tvResultado.text = "Resultado: $d1 y $d2 (suma $suma)"

            val opcion = spOpciones.selectedItem.toString()

            val gana: Boolean
            if (modo == 1) {
                val esPar = (suma % 2 == 0)
                gana = (opcion == "PAR" && esPar) || (opcion == "IMPAR" && !esPar)
            } else {
                gana = (opcion == "MAYOR O IGUAL QUE 7" && suma >= 7) ||
                        (opcion == "MENOR QUE 7" && suma < 7)
            }

            if (gana) {
                saldo += apuesta
                imgResultado.setImageResource(R.drawable.win)
            } else {
                saldo -= apuesta
                if (saldo <= 0) {
                    saldo = 0
                    imgResultado.setImageResource(R.drawable.bancarrota)
                } else {
                    imgResultado.setImageResource(R.drawable.lose)
                }
            }

            saldoNum.text = saldo.toString()
            imgResultado.visibility = View.VISIBLE

            if (saldo > 0) {
                dados.postDelayed({
                    AlertDialog.Builder(this)
                        .setTitle("¿DESEA SEGUIR JUGANDO?")
                        .setCancelable(false)
                        .setPositiveButton("SI") { dialog, which ->
                            btnLanzar.isEnabled = true
                            etApuesta.setText("")
                        }
                        .setNegativeButton("NO") { dialog, which ->
                            btnLanzar.isEnabled = false
                            etApuesta.setText("")
                        }
                        .show()
                }, 1000)
            }

        }, 3000)
    }
}
