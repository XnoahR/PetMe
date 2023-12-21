package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(

	@field:SerializedName("data")
	val data: Data1? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data1(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
