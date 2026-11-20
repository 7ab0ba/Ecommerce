package com.example.ecommerce.ui.theme.feature.network.models.response.auth

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val userDto: UserDto? = null,

	@field:SerializedName("token")
	val token: String? = null
)