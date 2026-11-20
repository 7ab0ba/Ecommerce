package com.example.ecommerce.ui.theme.feature.commerce.domain.use_cases

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.repository.HomeRepository
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(val homeRepository: HomeRepository) {
    suspend fun invoke(): ApiResult<List<CategoryItem>> =
        homeRepository.getCategory()
}



