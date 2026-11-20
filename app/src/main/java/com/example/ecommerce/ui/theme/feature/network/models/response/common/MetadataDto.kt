package com.example.ecommerce.ui.theme.feature.network.models.response.common

import com.google.gson.annotations.SerializedName

data class MetadataDto(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)