package com.example.apidog.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apidog.R
import com.example.apidog.model.DogRespuesta

class MiAdaptador(var dataSet: MutableList<String>) : RecyclerView.Adapter<MiVista>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiVista {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_row, parent, false)
        return MiVista(vista)
    }

    override fun onBindViewHolder(holder: MiVista, position: Int) {
        val url = dataSet[position]

        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.imV1)
    }

    override fun getItemCount(): Int {
        return dataSet.size

    }
}