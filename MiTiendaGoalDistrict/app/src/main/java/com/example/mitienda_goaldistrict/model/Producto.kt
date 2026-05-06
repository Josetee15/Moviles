package com.example.mitienda_goaldistrict.model

data class Producto(
    val productId: Long,
    val productCode: Long?,
    val productName: String,
    val productDescription: String?,
    val productImage: String?,
    val productPrice: Double,
    val productDiscount: Int?,
    val categories: List<Categoria>?
)
