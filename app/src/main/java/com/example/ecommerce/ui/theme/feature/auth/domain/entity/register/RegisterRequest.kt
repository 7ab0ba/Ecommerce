package com.example.ecommerce.ui.theme.feature.network.models.request.auth.register


data class RegisterRequest(

    val password: String? = null,

    val phone: String? = null,

    val rePassword: String? = null,

    val name: String? = null,

    val email: String? = null
)