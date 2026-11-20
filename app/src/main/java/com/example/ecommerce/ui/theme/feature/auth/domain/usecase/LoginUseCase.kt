package com.example.ecommerce.ui.theme.feature.auth.domain.usecase

import com.example.ecommerce.ui.theme.feature.auth.domain.repository.AuthRepository
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequest
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend fun invoke(LoginRequest: LoginRequest): ApiResult<Unit> =
        authRepository.login(LoginRequest)

}