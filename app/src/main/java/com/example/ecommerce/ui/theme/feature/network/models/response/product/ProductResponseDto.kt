package com.example.ecommerce.ui.theme.feature.network.models.response.product

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ProductResponseDto(

    @field:SerializedName("metadata")
    val metadata: JsonObject? = null,

    @field:SerializedName("data")
    val data: List<ProductItemDto?>? = null,

    @field:SerializedName("results")
    val results: Int? = null
)
