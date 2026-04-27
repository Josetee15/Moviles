package com.example.recyclerview_colores.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_colores.R
import com.example.recyclerview_colores.model.Datos

class MiAdaptador(var dataSet: List<Datos>) : RecyclerView.Adapter<MiVista>() {

    var posicionSeleccionada = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiVista {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)
        return MiVista(vista)
    }

    override fun onBindViewHolder(holder: MiVista, position: Int) {
        val colorActual = dataSet[position]

        holder.miTexto.text = colorActual.nombre
        holder.miTextoHexa.text = colorActual.hexa

        val colorFondo = Color.parseColor(colorActual.hexa)

        if (position == posicionSeleccionada) {
            holder.itemView.setBackgroundColor(Color.WHITE)
            holder.miTexto.setTextColor(colorFondo)
            holder.miTextoHexa.setTextColor(colorFondo)
        } else {
            holder.itemView.setBackgroundColor(colorFondo)
            holder.miTexto.setTextColor(Color.WHITE)
            holder.miTextoHexa.setTextColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = holder.bindingAdapterPosition
            notifyItemChanged(posicionSeleccionada)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}