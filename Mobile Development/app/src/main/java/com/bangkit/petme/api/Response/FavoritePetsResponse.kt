package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class FavoritePetsResponse(

	@field:SerializedName("FavoritePetsResponse")
	val favoritePetsResponse: List<FavoritePetsResponseItem?>? = null
)

data class Post(

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("id_animal")
	val idAnimal: Int? = null,

	@field:SerializedName("breed")
	val breed: String? = null,

	@field:SerializedName("post_picture")
	val postPicture: String? = null,

	@field:SerializedName("upload_date")
	val uploadDate: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null
)

data class FavoritePetsResponseItem(

	@field:SerializedName("post")
	val post: Post? = null,

	@field:SerializedName("id_post")
	val idPost: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null
)
