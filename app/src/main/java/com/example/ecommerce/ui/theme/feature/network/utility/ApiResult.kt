package com.example.ecommerce.ui.theme.feature.network.utility

import com.example.ecommerce.ui.theme.core.errors.AppErrors

sealed interface ApiResult<out T> {

    data class SuccessApiResult<T>(
        val data: T?
    ) : ApiResult<T>

    data class ErrorApiResult(
        val errors: AppErrors
    ) : ApiResult<Nothing>
}