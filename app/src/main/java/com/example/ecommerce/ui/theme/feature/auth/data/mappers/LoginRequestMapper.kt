package com.example.ecommerce.ui.theme.feature.auth.data.mappers

import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequest
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequestDto

fun LoginRequest.toLoginRequestDto(): LoginRequestDto {
    return LoginRequestDto(
        password = this.password,
        email = this.email
    )
}