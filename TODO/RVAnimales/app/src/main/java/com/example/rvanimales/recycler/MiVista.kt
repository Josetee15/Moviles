package com.example.rvanimales.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvanimales.R

class MiVista(miFila : View) : RecyclerView.ViewHolder(miFila) {
    var miTexto = miFila.findViewById<TextView>(R.id.txt1)
}