package com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.remote.home

import com.example.ecommerce.ui.theme.feature.network.models.response.category.CategoryResponseDto
import com.example.ecommerce.ui.theme.feature.network.models.response.product.ProductResponseDto
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult

interface HomeRemoteDataSource {

    suspend fun getCategory(): ApiResult<CategoryResponseDto>
    suspend fun getBrands(): ApiResult<CategoryResponseDto>
    suspend fun getProduct(): ApiResult<ProductResponseDto>

}