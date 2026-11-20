package com.example.ecommerce.ui.theme.feature.auth.data.datasource.remote

import com.example.ecommerce.ui.theme.feature.network.api.ApiServices
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequestDto
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequestDto
import com.example.ecommerce.ui.theme.feature.network.models.response.auth.AuthResponseDto
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import safeApiCall
import javax.inject.Inject


class AuthRemoteDataSourceImpl @Inject constructor(val apiServices: ApiServices) :
    AuthRemoteDataSource {
    override suspend fun login(loginRequestDto: LoginRequestDto): ApiResult<AuthResponseDto> =
        safeApiCall {
            apiServices.login(loginRequest = loginRequestDto)
        }

    override suspend fun register(registerRequestDto: RegisterRequestDto): ApiResult<AuthResponseDto> =
        safeApiCall {
            apiServices.register(registerRequest = registerRequestDto)
        }
}