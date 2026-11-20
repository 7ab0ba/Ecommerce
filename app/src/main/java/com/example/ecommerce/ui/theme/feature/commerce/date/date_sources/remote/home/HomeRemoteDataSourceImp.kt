package com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.remote.home

import com.example.ecommerce.ui.theme.feature.network.api.ApiServices
import com.example.ecommerce.ui.theme.feature.network.models.response.category.CategoryResponseDto
import com.example.ecommerce.ui.theme.feature.network.models.response.product.ProductResponseDto
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import safeApiCall
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(val apiServices: ApiServices) :
    HomeRemoteDataSource {
    override suspend fun getCategory(): ApiResult<CategoryResponseDto> = safeApiCall {
        apiServices.getAllCategories()
    }

    override suspend fun getBrands(): ApiResult<CategoryResponseDto> = safeApiCall {
        apiServices.getAllBrands()
    }

    override suspend fun getProduct(): ApiResult<ProductResponseDto> = safeApiCall {
        apiServices.getAllProducts()
    }
}