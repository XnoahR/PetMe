package com.bangkit.petme.api.Response

import com.google.gson.annotations.SerializedName

data class DeletePostResponse(

	@field:SerializedName("message")
	val message: String
)
