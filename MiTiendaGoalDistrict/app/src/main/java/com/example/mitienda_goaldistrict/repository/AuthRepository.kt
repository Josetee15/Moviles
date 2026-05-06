package com.example.mitienda_goaldistrict.repository

import com.example.mitienda_goaldistrict.api.RetrofitClient
import com.example.mitienda_goaldistrict.model.LoginRequest

class AuthRepository {

    suspend fun login(username: String, password: String) =
        RetrofitClient.apiService.login(LoginRequest(username, password))
}