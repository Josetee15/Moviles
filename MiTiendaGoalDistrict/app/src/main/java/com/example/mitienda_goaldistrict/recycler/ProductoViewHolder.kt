package com.example.mitienda_goaldistrict.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mitienda_goaldistrict.R
import com.example.mitienda_goaldistrict.databinding.FilaProductoBinding
import com.example.mitienda_goaldistrict.model.Producto

class ProductoViewHolder(
    private val binding: FilaProductoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: Producto) {
        // mostramos los datos básicos del producto en la fila
        binding.tvNombreProducto.text = producto.productName
        binding.tvDescripcionProducto.text = producto.productDescription ?: "Sin descripción"
        binding.tvPrecioProducto.text = "${producto.productPrice} €"

        // cargamos la imagen del producto si existe
        if (!producto.productImage.isNullOrEmpty()) {
            val urlImagen = obtenerUrlImagen(producto.productImage)

            Glide.with(binding.root.context)
                .load(urlImagen)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgProducto)
        } else {
            binding.imgProducto.setImageResource(R.mipmap.ic_launcher)
        }
    }

    private fun obtenerUrlImagen(imagen: String): String {
        // si la imagen ya viene con url completa, la usamos directamente
        return if (imagen.startsWith("http")) {
            imagen
        } else {
            // las imágenes están en el módulo web, no en la api
            "http://10.0.2.2:8080/assets/images/products/$imagen"
        }
    }
}