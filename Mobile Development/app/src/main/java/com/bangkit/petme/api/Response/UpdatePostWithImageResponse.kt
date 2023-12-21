package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class UpdatePostWithImageResponse(

	@field:SerializedName("data")
	val data: List<Int?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)
