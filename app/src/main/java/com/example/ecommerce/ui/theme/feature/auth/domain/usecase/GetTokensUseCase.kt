package com.example.ecommerce.ui.theme.feature.auth.domain.usecase

import com.example.ecommerce.ui.theme.feature.auth.data.datasource.local.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    val authLocalDataSource: AuthLocalDataSource
) {
    suspend fun invoke(): Flow<String?> =
        authLocalDataSource.tokenFlow
}