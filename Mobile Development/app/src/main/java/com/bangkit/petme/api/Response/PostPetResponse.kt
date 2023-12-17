package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class PostPetResponse(

	@field:SerializedName("PostPetResponse")
	val postPetResponse: List<PostPetResponseItem>
)

data class PostPetResponseItem(

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
