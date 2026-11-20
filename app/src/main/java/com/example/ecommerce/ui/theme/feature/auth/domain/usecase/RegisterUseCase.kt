package com.example.ecommerce.ui.theme.feature.auth.domain.usecase

import com.example.ecommerce.ui.theme.feature.auth.domain.repository.AuthRepository
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequest
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(val authRepository: AuthRepository) {
    suspend fun invoke(registerRequest: RegisterRequest): ApiResult<Unit> =
        authRepository.register(registerRequest)

}