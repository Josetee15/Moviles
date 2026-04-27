package com.example.recyclerview_colores.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_colores.R

class MiVista(miFila : View) : RecyclerView.ViewHolder(miFila) {

    var miTexto = miFila.findViewById<TextView>(R.id.txt1)
    val miTextoHexa = miFila.findViewById<TextView>(R.id.miTextoHexa)
}