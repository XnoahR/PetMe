package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class DetailPetResponse(

	@field:SerializedName("data")
	val data: DataDetail,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("favCheck")
	val favCheck: List<FavCheckItem>,

	@field:SerializedName("user")
	val user: Int,

	@field:SerializedName("isFav")
	val isFav: Boolean
)

data class DataDetail(

	@field:SerializedName("latitude")
	val latitude: Any,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("id_animal")
	val idAnimal: Int,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("breed")
	val breed: String,

	@field:SerializedName("post_picture")
	val postPicture: String,

	@field:SerializedName("upload_date")
	val uploadDate: String,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("longitude")
	val longitude: Any
)

data class FavCheckItem(

	@field:SerializedName("id_post")
	val idPost: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int
)

data class UserDetail(

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("username")
	val username: String
)
