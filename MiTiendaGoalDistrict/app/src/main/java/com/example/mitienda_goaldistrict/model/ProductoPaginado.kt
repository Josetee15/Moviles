package com.example.mitienda_goaldistrict.model

data class ProductoPaginado(
    val content: List<Producto>,
    val totalPages: Int,
    val totalElements: Long,
    val number: Int,
    val size: Int,
    val first: Boolean,
    val last: Boolean
)
