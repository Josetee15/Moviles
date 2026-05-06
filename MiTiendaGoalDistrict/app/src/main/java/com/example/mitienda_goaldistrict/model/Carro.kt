package com.example.mitienda_goaldistrict.model

data class Carro(
    val products: List<ItemCarro>,
    val distinctProducts: Long,
    val totalQuantity: Long,
    val totalAmount: Double
    )
