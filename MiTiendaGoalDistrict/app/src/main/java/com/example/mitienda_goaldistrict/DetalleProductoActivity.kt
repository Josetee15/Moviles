package com.example.mitienda_goaldistrict

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mitienda_goaldistrict.databinding.ActivityDetalleProductoBinding
import com.example.mitienda_goaldistrict.viewmodel.CarroViewModel

class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleProductoBinding
    private val carroViewModel: CarroViewModel by viewModels()

    private var token: String = ""
    private var productId: Long = 0
    private var productName: String = ""
    private var productDescription: String = ""
    private var productImage: String = ""
    private var productPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recogerDatosProducto()
        mostrarDatosProducto()
        configurarBotones()
        configurarObservadores()
    }

    private fun recogerDatosProducto() {
        // recogemos el token y los datos del producto que vienen desde ProductosFragment
        token = intent.getStringExtra("TOKEN") ?: ""
        productId = intent.getLongExtra("PRODUCT_ID", 0)
        productName = intent.getStringExtra("PRODUCT_NAME") ?: ""
        productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION") ?: "Sin descripción"
        productImage = intent.getStringExtra("PRODUCT_IMAGE") ?: ""
        productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
    }

    private fun mostrarDatosProducto() {
        // mostramos en pantalla los datos del producto seleccionado
        binding.tvNombreDetalle.text = productName
        binding.tvDescripcionDetalle.text = productDescription
        binding.tvPrecioDetalle.text = "$productPrice €"

        // cargamos la imagen del producto si existe
        if (productImage.isNotEmpty()) {
            Glide.with(this)
                .load(obtenerUrlImagen(productImage))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgDetalleProducto)
        } else {
            binding.imgDetalleProducto.setImageResource(R.mipmap.ic_launcher)
        }
    }

    private fun configurarBotones() {
        binding.btnAnadirCarro.setOnClickListener {
            val cantidadTexto = binding.etCantidad.text.toString()

            if (cantidadTexto.isNotEmpty()) {
                val cantidad = cantidadTexto.toInt()

                if (cantidad > 0) {
                    // enviamos al viewmodel el producto y la cantidad para añadirlo al carro usando la api
                    carroViewModel.addProductoCarro(token, productId, cantidad)
                } else {
                    Toast.makeText(this, "La cantidad debe ser mayor que 0", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Introduce una cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVolverProductos.setOnClickListener {
            // volvemos a la pantalla anterior, que era la pestaña de productos
            finish()
        }
    }

    private fun configurarObservadores() {
        // si el producto se añade correctamente, avisamos y volvemos a productos
        carroViewModel.productoAnadido.observe(this) { anadido ->
            if (anadido) {
                Toast.makeText(this, "Producto añadido al carro", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // si hay error en la llamada api, lo mostramos
        carroViewModel.error.observe(this) { mensaje ->
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
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