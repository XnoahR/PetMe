package com.bangkit.petme.repository

import android.content.Context
import com.bangkit.petme.api.Response.DeletePostResponse
import com.bangkit.petme.api.Response.DetailPetResponse
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.api.Retrofit.ApiConfig

class PetsCollectionRepository(application: Context) {
    suspend fun getPetsCollection(token: String): List<PetCollectionResponseItem>{
        return ApiConfig.getApiService().getPetCollection("Bearer $token")
    }

    suspend fun getFavoritePets(token: String): List<FavoritePetsResponseItem>{
        return  ApiConfig.getApiService().getFavoritePet("Bearer $token")
    }

    suspend fun getPostPet(token: String): List<PostPetResponseItem>{
        return ApiConfig.getApiService().getPostPet("Bearer $token")
    }

    suspend fun getDetailPost(token: String, id: Int): DetailPetResponse{
        return ApiConfig.getApiService().getDetailPet("Bearer $token", id)
    }

    suspend fun deletePost(token: String, id: Int): DeletePostResponse{
        return ApiConfig.getApiService().deletePost("Bearer $token",id)
    }

}