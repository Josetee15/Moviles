package com.example.mitienda_goaldistrict.repository

import com.example.mitienda_goaldistrict.api.RetrofitClient

class ProductoRepository {
    suspend fun getCategorias(token: String) =
        RetrofitClient.apiService.getCategorias("Bearer $token")

    suspend fun getProductosPaginados(token: String, pagina: Int, tamanoPagina: Int) =
        RetrofitClient.apiService.getProductosPaginados("Bearer $token", pagina, tamanoPagina)

    suspend fun getProductosPorCategoriaPaginados(
        token: String,
        categoryId: Long,
        pagina: Int,
        tamanoPagina: Int) =
        RetrofitClient.apiService.getProductosPorCategoriaPaginados(
            "Bearer $token",
            categoryId,
            pagina,
            tamanoPagina
        )
}