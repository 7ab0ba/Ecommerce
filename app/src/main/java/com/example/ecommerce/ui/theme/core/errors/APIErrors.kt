package com.example.ecommerce.ui.theme.core.errors

sealed class AppErrors(val errorMessage: String) {

    data class NetworkError(val message: String = "No Internet Connection please try again later") :
        AppErrors(errorMessage = message)

    data class ServerError(val message: String = "Server down please try again later") :
        AppErrors(errorMessage = message)

    data class UnKmpwnError(val message: String = "SSomething went wrong please try again later") :
        AppErrors(errorMessage = message)
}