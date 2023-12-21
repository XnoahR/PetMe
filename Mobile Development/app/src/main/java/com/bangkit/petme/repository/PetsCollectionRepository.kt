package com.bangkit.petme.repository

import android.content.Context
import com.bangkit.petme.api.Response.DeletePostResponse
import com.bangkit.petme.api.Response.DetailPetResponse
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.api.Response.UpdatePostResponse
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

    suspend fun updatePostNoImage(token: String, id: Int, title: String, breed: String, description: String, idAnimal: String, longitude:String, latitude:String): UpdatePostResponse{
        val body = mapOf(
            "title" to title,
            "bread" to breed,
            "description" to description,
            "id_animal" to idAnimal,
            "longitude" to longitude,
            "latitude" to latitude
        )

        return ApiConfig.getApiService().updatePostNoImage("Bearer $token", id, body)
    }

}