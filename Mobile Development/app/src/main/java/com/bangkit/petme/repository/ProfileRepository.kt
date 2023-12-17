package com.bangkit.petme.repository

import android.app.Application
import com.bangkit.petme.api.Response.EditProfileResponse
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Response.UserProfileResponse
import com.bangkit.petme.api.Response.UserProfileResponseItem
import com.bangkit.petme.api.Retrofit.ApiConfig

class ProfileRepository(application: Application) {
    suspend fun getUserProfile(token: String): List<UserProfileResponseItem?>{
        return ApiConfig.getApiService().getUserProfile("Bearer $token")
    }

    suspend fun editProfile(token: String, id: Int, name: String, email: String, phone: String, password: String): EditProfileResponse{
        val body = mapOf(
            "name" to name,
            "email" to email,
            "phone" to phone,
            "password" to password
        )

        return ApiConfig.getApiService().editUser("Bearer $token", id, body)
    }
}