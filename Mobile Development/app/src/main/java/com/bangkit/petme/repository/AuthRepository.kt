package com.bangkit.petme.repository

import android.app.Application
import com.bangkit.petme.api.Response.LoginResponse
import com.bangkit.petme.api.Response.RegisterResponse
import com.bangkit.petme.api.Retrofit.ApiConfig

class AuthRepository(application: Application) {
    suspend fun login(email: String, password: String): LoginResponse{
        val loginData = mapOf(
            "email" to email,
            "password" to password
        )
        return ApiConfig.getApiService().login(loginData)
    }

    suspend fun register(name: String, email: String, phone: String, password: String) : RegisterResponse{
        val registerData = mapOf(
            "name" to name,
            "email" to email,
            "phone" to phone,
            "password" to password
        )
        return ApiConfig.getApiService().register(registerData)
    }
}

