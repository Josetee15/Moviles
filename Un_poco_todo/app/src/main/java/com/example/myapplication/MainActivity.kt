package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener,
        (DialogInterface, Int) -> Unit {

     lateinit var des : Spinner
     lateinit var text : TextView
     lateinit var btn : Button

     lateinit var main : ConstraintLayout

     lateinit var div : CoordinatorLayout
    lateinit var cuadro : ImageView

     lateinit var miVista : View


    val miArray = arrayOf("España", "Japon", "Canada", "Brasil")

     lateinit var miAdaptador : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        des = this.findViewById(R.id.spinner)
        text = this.findViewById(R.id.textView1)
        btn = this.findViewById(R.id.button16)
        div = this.findViewById(R.id.div)


        des.onItemSelectedListener = this

        btn.setOnClickListener(this)

        miAdaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, miArray)
        des.adapter = miAdaptador


    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        var opcion = des.selectedItem.toString()
        text.text = opcion
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        //Toast.makeText(this, "Texto que quiero", Toast.LENGTH_SHORT).show()
        /*Snackbar.make(div, "Probando Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("Utilizando acciones", View.OnClickListener{
                text.text = "He pulsando el Snackbar"
            })
            .show()*/
       /* var miAlert = AlertDialog.Builder(this)

        miAlert.setTitle("DA2D1E")
        miVista = layoutInflater.inflate(R.layout.login2, null)
        miAlert.setView(miVista)
        //miAlert.setMessage("Estoy practicando los mensajes")
        miAlert.setPositiveButton("OK", DialogInterface.OnClickListener(this))
        miAlert.setNegativeButton("KO", DialogInterface.OnClickListener(this))
        miAlert.setNeutralButton("MEH", DialogInterface.OnClickListener(this))

        miAlert.show()*/

        cuadro = this.findViewById(R.id.cuadro)

        Glide.with(this)
            .load(R.drawable.dado_imagen_animada_0092)
            .into(cuadro)
    }

    override fun invoke(p1: DialogInterface, p2: Int) {
        var username = miVista.findViewById<EditText>(R.id.username)
        text.text = username.text.toString()
    }


}