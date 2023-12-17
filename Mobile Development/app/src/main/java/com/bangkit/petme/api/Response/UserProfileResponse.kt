package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(

	@field:SerializedName("UserProfileResponse")
	val userProfileResponse: List<UserProfileResponseItem?>? = null
)

data class UserProfileResponseItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("role")
	val role: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("profile_picture")
	val profilePicture: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
