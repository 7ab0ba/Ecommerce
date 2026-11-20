package com.example.ecommerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.ui.theme.core.navigation.LoginRoute
import com.example.ecommerce.ui.theme.core.navigation.MainTabRoute
import com.example.ecommerce.ui.theme.core.navigation.SplashRoute
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.local.AuthLocalDataSource
import com.example.ecommerce.ui.theme.feature.auth.domain.usecase.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getTokenUseCase: GetTokenUseCase,
    val authLocalDataSource: AuthLocalDataSource
) : ViewModel() {

    private val _startDestination: MutableStateFlow<Any?> =
        MutableStateFlow(value = null)

    val startDestination = _startDestination.asStateFlow()

    init {
        checkAppStartFlow()
    }

    fun checkAppStartFlow() {
        viewModelScope.launch {
            try {
                val isFirstTime = authLocalDataSource.isFirstTime.first()
                val token = getTokenUseCase.invoke().first()

                _startDestination.value = when {
                    isFirstTime -> SplashRoute
                    !token.isNullOrBlank() -> MainTabRoute
                    else -> LoginRoute
                }

            } catch (e: Exception) {
                _startDestination.value = LoginRoute
            }
        }
    }

    fun onSplashFinished() {
        viewModelScope.launch {
            authLocalDataSource.setFirstTimeCompleted()
        }
    }
}
