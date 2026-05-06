package com.example.mitienda_goaldistrict.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mitienda_goaldistrict.databinding.FilaProductoBinding
import com.example.mitienda_goaldistrict.model.Producto

class ProductoAdapter(
    private var listaProductos: List<Producto>,
    private val onProductoClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        // inflamos el xml de cada fila del recycler
        val binding = FilaProductoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]

        // pintamos los datos del producto en la fila
        holder.bind(producto)

        // cuando se pulsa un producto, avisamos al fragment
        holder.itemView.setOnClickListener {
            onProductoClick(producto)
        }
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

    fun actualizarProductos(nuevaLista: List<Producto>) {
        // sustituimos la lista actual por la nueva lista recibida desde la api
        listaProductos = nuevaLista
        notifyDataSetChanged()
    }
}