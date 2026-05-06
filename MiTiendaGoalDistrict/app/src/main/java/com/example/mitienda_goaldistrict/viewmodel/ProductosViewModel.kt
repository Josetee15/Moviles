package com.example.mitienda_goaldistrict.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitienda_goaldistrict.model.Categoria
import com.example.mitienda_goaldistrict.model.Producto
import com.example.mitienda_goaldistrict.repository.ProductoRepository
import kotlinx.coroutines.launch

class ProductosViewModel: ViewModel() {

    private val repository = ProductoRepository()

    val productos = MutableLiveData<List<Producto>>()
    val categorias = MutableLiveData<List<Categoria>>()
    val error = MutableLiveData<String>()

    val paginaActual = MutableLiveData<Int>()
    val totalPaginas = MutableLiveData<Int>()

    fun cargarCategorias(token: String){
        viewModelScope.launch {
            try {
                // cargamos las categorías desde la api para usarlas en el filtro
                val response = repository.getCategorias(token)

                if(response.isSuccessful){
                    // guardamos la lista, si no llega nada usamos lista vacía
                    categorias.value = response.body() ?: emptyList()
                } else {
                    error.value = "Error al cargar las categorias"
                }
            } catch (e: Exception){
                error.value = "Error de conexión"
            }
        }
    }

    fun cargarProductos(token: String, pagina: Int, tamanoPagina: Int){
        viewModelScope.launch {
            try {
                // pedimos a la api una pagina concreta
                val response = repository.getProductosPaginados(token, pagina, tamanoPagina)

                if (response.isSuccessful){
                    val resultado = response.body()

                    // si la respuesta tiene productos, los guardamos; si no, dejamos la lista vacía
                    productos.value = resultado?.content ?: emptyList()
                    // guardamos la página actual y el total de páginas para controlar los botones anterior/siguiente
                    paginaActual.value = resultado?.number ?: 0
                    totalPaginas.value = resultado?.totalPages ?: 0
                } else {
                    error.value = "Error al cargar los productos"
                }
            } catch (e: Exception){
                error.value = "Error de conexion"
            }
        }
    }
    fun cargarProductosPorCategoria(
        token: String, categoryId: Long, pagina: Int, tamanoPagina: Int
    ) {
        viewModelScope.launch {
            try {
                // cargamos productos filtrados por categoría y paginados
                val response = repository.getProductosPorCategoriaPaginados(
                    token, categoryId, pagina, tamanoPagina)
                if (response.isSuccessful){
                    val resultado = response.body()

                    productos.value = resultado?.content ?: emptyList()
                    paginaActual.value = resultado?.number ?: 0
                    totalPaginas.value = resultado?.totalPages ?: 0
                } else {
                    error.value = "Error al cargar los productos"
                }
            } catch (e: Exception){
                error.value = "Error de conexion"
            }
        }
    }
}