package com.example.mitienda_goaldistrict.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mitienda_goaldistrict.databinding.FilaCarroBinding
import com.example.mitienda_goaldistrict.model.ItemCarro

class CarroAdapter(
    private var listaCarro: List<ItemCarro>,
    private val onItemClick: (ItemCarro) -> Unit
) : RecyclerView.Adapter<CarroViewHolder>() {

    private var posicionSeleccionada = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        // inflamos el xml de cada fila del recycler del carro
        val binding = FilaCarroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CarroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val itemCarro = listaCarro[position]

        // pintamos los datos del producto del carro en la fila
        holder.bind(itemCarro, position == posicionSeleccionada)

        // cuando se pulsa un producto, lo marcamos como seleccionado y avisamos al fragment
        holder.itemView.setOnClickListener {
            val posicion = holder.bindingAdapterPosition

            if (posicion != RecyclerView.NO_POSITION) {
                posicionSeleccionada = posicion
                notifyDataSetChanged()
                onItemClick(listaCarro[posicion])
            }
        }
    }

    override fun getItemCount(): Int {
        return listaCarro.size
    }

    fun actualizarCarro(nuevaLista: List<ItemCarro>) {
        // sustituimos la lista actual por la nueva lista recibida desde la api
        listaCarro = nuevaLista
        posicionSeleccionada = -1
        notifyDataSetChanged()
    }
}