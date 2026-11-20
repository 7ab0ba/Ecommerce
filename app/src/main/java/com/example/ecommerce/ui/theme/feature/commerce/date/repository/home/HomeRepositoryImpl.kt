package com.example.ecommerce.ui.theme.feature.commerce.domain.repository

import com.example.ecommerce.ui.theme.core.errors.AppErrors
import com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.mappers.toCategory
import com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.mappers.toProductItem
import com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.remote.home.HomeRemoteDataSource
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.network.models.response.category.CategoryResponseDto
import com.example.ecommerce.ui.theme.feature.network.models.response.product.ProductResponseDto
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun getCategory(): ApiResult<List<CategoryItem>> {

        return when (val result = homeRemoteDataSource.getCategory()) {
            is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(
                errors = AppErrors.ServerError(message = result.errors.errorMessage)
            )

            is ApiResult.SuccessApiResult<CategoryResponseDto> -> {
                //todo: List<CategoryItemDto> => List<CategoryItem>
                ApiResult.SuccessApiResult(
                    data = result.data?.data?.map { categoryItemDto ->
                        return@map categoryItemDto?.toCategory()
                    }?.filterNotNull()
                )
            }
        }
    }

    override suspend fun getBrands(): ApiResult<List<CategoryItem>> {
        return when (val result = homeRemoteDataSource.getBrands()) {
            is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(
                errors = AppErrors.ServerError(
                    message = result.errors.errorMessage
                )
            )

            is ApiResult.SuccessApiResult<CategoryResponseDto> -> {
                ApiResult.SuccessApiResult(
                    data = result.data?.data?.map { categoryItemDto ->
                        return@map categoryItemDto?.toCategory()
                    }?.filterNotNull()
                )
            }
        }
    }

    override suspend fun getProducts(): ApiResult<List<ProductItem>> {
        return when (val result = homeRemoteDataSource.getProduct()) {
            is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(
                errors = AppErrors.ServerError(
                    message = result.errors.errorMessage
                )
            )

            is ApiResult.SuccessApiResult<ProductResponseDto> -> {
                //todo: List<ProductItemDto> => List<ProductItem>
                ApiResult.SuccessApiResult(
                    data = result.data?.data?.map { productItemDto ->
                        return@map productItemDto?.toProductItem()
                    }?.filterNotNull() ?: emptyList()
                )
            }
        }
    }
}
