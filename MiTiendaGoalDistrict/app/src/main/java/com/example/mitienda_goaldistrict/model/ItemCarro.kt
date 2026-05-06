package com.example.mitienda_goaldistrict.model

data class ItemCarro(
    val productId: Long,
    val productName: String,
    val unitPrice: Double,
    val discount: Int?,
    val discountedPrice: Double,
    val quantity: Int,
    val totalPrice: Double
)
