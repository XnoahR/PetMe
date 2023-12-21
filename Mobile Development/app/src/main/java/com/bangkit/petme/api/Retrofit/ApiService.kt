package com.bangkit.petme.api.Retrofit

import com.bangkit.petme.api.Response.AddFavoriteResponse
import com.bangkit.petme.api.Response.AddPostResponse
import com.bangkit.petme.api.Response.AnnouncementResponse
import com.bangkit.petme.api.Response.DataItem
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
import com.bangkit.petme.api.Response.UpdatePostResponse
import com.bangkit.petme.api.Response.UpdatePostWithImageResponse
import com.bangkit.petme.api.Response.UserProfileResponse
import com.bangkit.petme.api.Response.UserProfileResponseItem
import com.bangkit.petme.ui.detail.DetailPetActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
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

    @Multipart
    @POST("post/create")
    fun uploadImage(
        @Header("Authorization") token: String,
        @Part("title") title: RequestBody,
        @Part("breed") breed: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_animal") idAnimal: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part file: MultipartBody.Part
        ): Call<AddPostResponse>

    @GET("notification/")
    fun getAnnouncement(
        @Header("Authorization") token: String,
    ): Call<AnnouncementResponse>

    @PATCH("post/edit/{id}")
    suspend fun updatePostNoImage(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body update: Map<String, String>
    ): UpdatePostResponse

    @Multipart
    @PATCH("post/edit/pict/{id}")
    fun updateWithImage(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Part("title") title: RequestBody,
        @Part("breed") breed: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_animal") idAnimal: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<UpdatePostWithImageResponse>
}
