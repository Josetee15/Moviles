package com.example.mitienda_goaldistrict.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitienda_goaldistrict.DetalleProductoActivity
import com.example.mitienda_goaldistrict.databinding.FragmentProductosBinding
import com.example.mitienda_goaldistrict.model.Categoria
import com.example.mitienda_goaldistrict.model.Producto
import com.example.mitienda_goaldistrict.recycler.ProductoAdapter
import com.example.mitienda_goaldistrict.viewmodel.ProductosViewModel

class ProductosFragment : Fragment() {

    private var _binding: FragmentProductosBinding? = null
    private val binding get() = _binding!!

    private val productosViewModel: ProductosViewModel by viewModels()

    private lateinit var productoAdapter: ProductoAdapter

    private var token: String = ""
    private var paginaActual = 0
    private var totalPaginas = 0
    private val tamanoPagina = 5

    private var categorias: List<Categoria> = emptyList()
    private var categoriaSeleccionada: Categoria? = null
    private var primeraCargaSpinner = true

    companion object {
        fun newInstance(token: String): ProductosFragment {
            val fragment = ProductosFragment()
            val bundle = Bundle()

            // guardamos el token en los argumentos del fragment
            bundle.putString("TOKEN", token)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // recuperamos el token recibido desde la activity principal
        token = arguments?.getString("TOKEN") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecycler()
        configurarObservadores()
        configurarBotonesPaginacion()

        // cargamos categorías y la primera página de productos desde la api
        productosViewModel.cargarCategorias(token)
        productosViewModel.cargarProductos(token, paginaActual, tamanoPagina)
    }

    private fun configurarRecycler() {
        productoAdapter = ProductoAdapter(emptyList()) { producto ->
            abrirDetalleProducto(producto)
        }

        binding.recyclerProductos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerProductos.adapter = productoAdapter
    }

    private fun configurarObservadores() {
        // observamos los productos recibidos desde la api y actualizamos el recyclerview
        productosViewModel.productos.observe(viewLifecycleOwner) { listaProductos ->
            productoAdapter.actualizarProductos(listaProductos)
        }

        // observamos las categorías recibidas desde la api para mostrarlas en el spinner
        productosViewModel.categorias.observe(viewLifecycleOwner) { listaCategorias ->
            categorias = listaCategorias
            configurarSpinnerCategorias()
        }

        // observamos la página actual para actualizar el texto inferior
        productosViewModel.paginaActual.observe(viewLifecycleOwner) { pagina ->
            paginaActual = pagina
            actualizarTextoPagina()
        }

        // observamos el total de páginas para controlar la paginación
        productosViewModel.totalPaginas.observe(viewLifecycleOwner) { total ->
            totalPaginas = total
            actualizarTextoPagina()
        }

        // observamos los errores para mostrarlos al usuario
        productosViewModel.error.observe(viewLifecycleOwner) { mensaje ->
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurarSpinnerCategorias() {
        val nombresCategorias = mutableListOf("Todas las categorías")

        for (categoria in categorias) {
            nombresCategorias.add(categoria.categoryName)
        }

        val adapterSpinner = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            nombresCategorias
        )

        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategorias.adapter = adapterSpinner

        binding.spinnerCategorias.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // evitamos recargar dos veces al montar el spinner por primera vez
                    if (primeraCargaSpinner) {
                        primeraCargaSpinner = false
                        return
                    }

                    paginaActual = 0

                    categoriaSeleccionada = if (position == 0) {
                        null
                    } else {
                        categorias[position - 1]
                    }

                    // cargamos productos normales o filtrados según la categoría seleccionada
                    cargarProductosSegunFiltro()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun configurarBotonesPaginacion() {
        binding.btnAnterior.setOnClickListener {
            if (paginaActual > 0) {
                paginaActual--
                cargarProductosSegunFiltro()
            }
        }

        binding.btnSiguiente.setOnClickListener {
            if (paginaActual < totalPaginas - 1) {
                paginaActual++
                cargarProductosSegunFiltro()
            }
        }
    }

    private fun cargarProductosSegunFiltro() {
        val categoria = categoriaSeleccionada

        if (categoria == null) {
            // si no hay categoría seleccionada, cargamos todos los productos paginados
            productosViewModel.cargarProductos(token, paginaActual, tamanoPagina)
        } else {
            // si hay categoría seleccionada, cargamos productos filtrados y paginados
            productosViewModel.cargarProductosPorCategoria(
                token,
                categoria.categoryId,
                paginaActual,
                tamanoPagina
            )
        }
    }

    private fun actualizarTextoPagina() {
        val paginaMostrada = paginaActual + 1

        if (totalPaginas > 0) {
            binding.tvPagina.text = "Página $paginaMostrada de $totalPaginas"
        } else {
            binding.tvPagina.text = "Página 0 de 0"
        }
    }

    private fun abrirDetalleProducto(producto: Producto) {
        val intent = Intent(requireContext(), DetalleProductoActivity::class.java)

        // pasamos el token y los datos del producto a la activity de detalle
        intent.putExtra("TOKEN", token)
        intent.putExtra("PRODUCT_ID", producto.productId)
        intent.putExtra("PRODUCT_NAME", producto.productName)
        intent.putExtra("PRODUCT_DESCRIPTION", producto.productDescription)
        intent.putExtra("PRODUCT_IMAGE", producto.productImage)
        intent.putExtra("PRODUCT_PRICE", producto.productPrice)

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // limpiamos el binding cuando se destruye la vista del fragment
        _binding = null
    }
}