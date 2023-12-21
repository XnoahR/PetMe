package com.bangkit.petme.repository

import android.app.Application
import com.bangkit.petme.api.Response.AddFavoriteResponse
import com.bangkit.petme.api.Response.DeleteFavoriteResponse
import com.bangkit.petme.api.Retrofit.ApiConfig

class FavoriteRepository (application: Application) {
    suspend fun addFavorite(token: String, id: Int): AddFavoriteResponse{
        return ApiConfig.getApiService().addFavorite("Bearer $token", id)
    }

    suspend fun deleteFavorite(token: String, id: Int): DeleteFavoriteResponse {
        return ApiConfig.getApiService().deleteFavorite("Bearer $token", id)
    }
}