package com.example.ecommerce.ui.theme.feature.auth.data.repository

import android.util.Log
import com.example.ecommerce.ui.theme.core.errors.AppErrors
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.local.AuthLocalDataSource
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.remote.AuthRemoteDataSource
import com.example.ecommerce.ui.theme.feature.auth.data.mappers.toLoginRequestDto
import com.example.ecommerce.ui.theme.feature.auth.data.mappers.toRegisterRequestDto
import com.example.ecommerce.ui.theme.feature.auth.domain.repository.AuthRepository
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequest
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequest
import com.example.ecommerce.ui.theme.feature.network.models.response.auth.AuthResponseDto
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val authRemoteDataSource: AuthRemoteDataSource,
    val authLocalDataSource: AuthLocalDataSource
) :
    AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): ApiResult<Unit> {
//todo: LoginRequest => LoginRequestDto
        return when (val result =
            authRemoteDataSource.login(loginRequestDto = loginRequest.toLoginRequestDto())) {
            is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(
                errors = AppErrors.ServerError(
                    message = result.errors.errorMessage
                )
            )

            is ApiResult.SuccessApiResult<AuthResponseDto> -> {

                val token = result.data?.token
                if (!token.isNullOrBlank()) {
                    authLocalDataSource.saveToken(token = token)
                }
                Log.e("Token", "token: $token")



                ApiResult.SuccessApiResult(data = Unit)

            }
        }


    }


    override suspend fun register(registerRequest: RegisterRequest): ApiResult<Unit> {
//todo: RegisterRequest => RegisterRequestDto
        return when (val result =
            authRemoteDataSource.register(registerRequestDto = registerRequest.toRegisterRequestDto())) {
            is ApiResult.ErrorApiResult -> ApiResult.ErrorApiResult(
                errors = AppErrors.ServerError(
                    message = result.errors.errorMessage
                )
            )

            is ApiResult.SuccessApiResult<AuthResponseDto> -> {
                val token = result.data?.token
                if (!token.isNullOrBlank()) {
                    authLocalDataSource.saveToken(token = token)
                }
                Log.e("Token", "token: $token")


                ApiResult.SuccessApiResult(data = Unit)
            }
        }
    }
}