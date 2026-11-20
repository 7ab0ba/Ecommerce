package com.example.ecommerce.ui.theme.feature.commerce.domain.repository

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult

interface HomeRepository {
    suspend fun getCategory(): ApiResult<List<CategoryItem>>
    suspend fun getBrands(): ApiResult<List<CategoryItem>>
    suspend fun getProducts(): ApiResult<List<ProductItem>>
}