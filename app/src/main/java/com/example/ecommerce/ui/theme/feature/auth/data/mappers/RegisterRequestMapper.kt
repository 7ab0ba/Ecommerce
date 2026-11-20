package com.example.ecommerce.ui.theme.feature.auth.data.mappers

import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequest
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequestDto

fun RegisterRequest.toRegisterRequestDto(): RegisterRequestDto {
    return RegisterRequestDto(
        name = this.name,
        email = this.email,
        password = this.password,
        rePassword = this.rePassword,
        phone = this.phone
    )

}