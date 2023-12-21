package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class UpdatePostResponse(

	@field:SerializedName("message")
	val message: String? = null
)
