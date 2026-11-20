package com.example.ecommerce.ui.theme.feature.commerce.domain.use_cases

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.repository.HomeRepository
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(val homeRepository: HomeRepository) {

    suspend fun invoke(): ApiResult<List<ProductItem>> =
        homeRepository.getProducts()
}