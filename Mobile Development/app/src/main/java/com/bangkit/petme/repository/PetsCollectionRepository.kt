package com.bangkit.petme.repository

import android.app.Application
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.PetCollectionResponse
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Retrofit.ApiConfig
import com.bangkit.petme.api.Retrofit.ApiService
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.model.PetsCollection
import retrofit2.Response

class PetsCollectionRepository(application: Application) {
    suspend fun getPetsCollection(token: String): List<PetCollectionResponseItem>{
        return ApiConfig.getApiService().getPetCollection("Bearer $token")
    }

    suspend fun getFavoritePets(token: String): List<FavoritePetsResponseItem>{
        return  ApiConfig.getApiService().getFavoritePet("Bearer $token")
    }
}