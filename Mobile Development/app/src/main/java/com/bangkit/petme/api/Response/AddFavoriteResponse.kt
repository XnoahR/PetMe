package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class AddFavoriteResponse(

	@field:SerializedName("id_post")
	val idPost: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int
)
