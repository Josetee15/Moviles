package com.example.rvanimales.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rvanimales.R
import com.example.rvanimales.model.Datos

class MiAdaptador(var dataSet : Datos) : RecyclerView.Adapter<MiVista>() {
    //SIEMPRE IMPLEMENTA ESTOS TRES METODOS

    var posicionSeleccionada = RecyclerView.NO_POSITION


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiVista {
        var miVista = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)

        return MiVista(miVista)
    }



    override fun onBindViewHolder(
        holder: MiVista,
        position: Int
    ) {
        holder.miTexto.text = dataSet.lista[position]
        if(position == posicionSeleccionada){
            holder.miTexto.setTextColor(Color.WHITE)
            holder.miTexto.setBackgroundColor(Color.RED)
        } else {
            holder.miTexto.setTextColor(Color.RED)
            holder.miTexto.setBackgroundColor(Color.WHITE)
        }
        holder.miTexto.setOnClickListener {
            notifyItemChanged(posicionSeleccionada)
            posicionSeleccionada = position
            notifyItemChanged(posicionSeleccionada)
        }

       /* holder.miTexto.setOnLongClickListener {
            holder.miTexto.setTextColor(Color.RED)
            holder.miTexto.setBackgroundColor(Color.WHITE)
            true
        }*/
    }



    override fun getItemCount(): Int {
        return dataSet.lista.size
    }
}