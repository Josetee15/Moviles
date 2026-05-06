package com.example.mitienda_goaldistrict.repository

import com.example.mitienda_goaldistrict.api.RetrofitClient
import com.example.mitienda_goaldistrict.model.AddCarroRequest

class CarroRepository {
    suspend fun addProductoCarro(token: String, productId: Long, quantity: Int) =
        RetrofitClient.apiService.addProductoCarro(
            "Bearer $token",
            AddCarroRequest(productId, quantity)
        )

    suspend fun getCarro(token: String) =
        RetrofitClient.apiService.getCarro("Bearer $token")

    suspend fun deleteProductoCarro(token: String, productId: Long) =
        RetrofitClient.apiService.deleteProductoCarro("Bearer $token", productId)
}