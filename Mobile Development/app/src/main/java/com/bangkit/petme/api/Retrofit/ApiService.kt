package com.bangkit.petme.api.Retrofit

import com.bangkit.petme.api.Response.AddFavoriteResponse
import com.bangkit.petme.api.Response.DeleteFavoriteResponse
import com.bangkit.petme.api.Response.DeletePostResponse
import com.bangkit.petme.api.Response.DetailPetResponse
import com.bangkit.petme.api.Response.EditProfileResponse
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.LoginResponse
import com.bangkit.petme.api.Response.PetCollectionResponse
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.api.Response.RegisterResponse
import com.bangkit.petme.api.Response.UserProfileResponse
import com.bangkit.petme.api.Response.UserProfileResponseItem
import com.bangkit.petme.ui.detail.DetailPetActivity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("post/my")
    suspend fun getPetCollection(
        @Header("Authorization") token: String,
    ): List<PetCollectionResponseItem>


    @POST("account/login")
    suspend fun login(@Body loginRequest: Map<String, String>): LoginResponse

    @PATCH("user/edit/{id}")
    suspend fun editUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body editItem: Map<String, String>
    ): EditProfileResponse

    @GET("user")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
    ): List<UserProfileResponseItem?>

    @POST("account/register")
    suspend fun register(
        @Body register: Map<String, String>
    ): RegisterResponse

    @GET("user/fav")
    suspend fun getFavoritePet(
        @Header("Authorization") token: String,
        ): List<FavoritePetsResponseItem>

    @GET("post")
    suspend fun getPostPet(
        @Header("Authorization") token: String,
    ): List<PostPetResponseItem>

    @GET("post/find/{id}")
    suspend fun getDetailPet(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): DetailPetResponse

    @POST("post/find/{id}")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        ): AddFavoriteResponse

    @DELETE("post/favourite/{id}")
    suspend fun deleteFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): DeleteFavoriteResponse

    @DELETE("post/{id}")
    suspend fun deletePost(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): DeletePostResponse
}
