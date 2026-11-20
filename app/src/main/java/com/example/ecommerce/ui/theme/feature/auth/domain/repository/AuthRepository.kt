package com.example.ecommerce.ui.theme.feature.auth.domain.repository

import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequest
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequest
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult


interface AuthRepository {
    suspend fun register(registerRequest: RegisterRequest): ApiResult<Unit>
    suspend fun login(loginRequest: LoginRequest): ApiResult<Unit>
}