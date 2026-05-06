package com.example.mitienda_goaldistrict.recycler

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mitienda_goaldistrict.R
import com.example.mitienda_goaldistrict.databinding.FilaCarroBinding
import com.example.mitienda_goaldistrict.model.ItemCarro

class CarroViewHolder(
    private val binding: FilaCarroBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemCarro: ItemCarro, seleccionado: Boolean) {
        // mostramos los datos básicos del producto que está dentro del carro
        binding.tvNombreCarro.text = itemCarro.productName
        binding.tvCantidadCarro.text = "Cantidad: ${itemCarro.quantity}"
        binding.tvPrecioCarro.text = "Total: ${itemCarro.totalPrice} €"

        // resaltamos la fila seleccionada antes de mostrar el alertdialog
        if (seleccionado) {
            binding.layoutFilaCarro.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, R.color.verde_claro)
            )
        } else {
            binding.layoutFilaCarro.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, android.R.color.transparent)
            )
        }
    }
}