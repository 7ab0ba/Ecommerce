package com.example.ecommerce.ui.theme.feature.auth.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    //todo: get token
    val tokenFlow: Flow<String?>

    //todo: save token
    suspend fun saveToken(token: String)

    //todo: remove token
    suspend fun removeToken()

    //todo: first
    val isFirstTime: Flow<Boolean>

    //todo: edit finstTime => false
    suspend fun setFirstTimeCompleted()
    
}