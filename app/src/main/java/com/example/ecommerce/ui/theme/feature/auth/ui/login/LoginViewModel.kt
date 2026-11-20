package com.example.ecommerce.ui.theme.feature.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.ui.theme.feature.auth.domain.usecase.LoginUseCase
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.login.LoginRequest
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginStates(
    val email: String = "habiba@fue.com",
    val password: String = "12345678@Ha",
    val loginState: Resource<Unit> = Resource.Initial
)


sealed interface LoginIntent {
    data class EmailChanged(val email: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    data object OnLoginClicked : LoginIntent
    data object OnCreateAccountClicked : LoginIntent
}

//todo: Events , Effects
sealed interface LoginEffects {
    data class ShowToast(val message: String) : LoginEffects
    data object OnNavigateToRegister : LoginEffects
    data object OnNavigateToHome : LoginEffects


}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _states: MutableStateFlow<LoginStates> =
        MutableStateFlow(value = LoginStates())

    val states: StateFlow<LoginStates> = _states.asStateFlow()

    private val _effects: Channel<LoginEffects> = Channel<LoginEffects>(capacity = Channel.BUFFERED)
    val effects: Flow<LoginEffects> = _effects.receiveAsFlow()


    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> changeEmail(email = intent.email)
            LoginIntent.OnCreateAccountClicked -> onSignUpClicked()
            LoginIntent.OnLoginClicked -> login()
            is LoginIntent.PasswordChanged -> changePassword(password = intent.password)
        }
    }

    private fun changeEmail(email: String) {
        _states.value = _states.value.copy(email = email)
//        _states.update {
//            it.copy(email = email)
//        }
    }

    private fun changePassword(password: String) {
        _states.value = _states.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {

            // Loading
            _states.update {
                it.copy(loginState = Resource.Loading)
            }

            when (
                val result = loginUseCase.invoke(
                    LoginRequest = LoginRequest(
                        email = _states.value.email,
                        password = _states.value.password
                    )
                )
            ) {

                is ApiResult.ErrorApiResult -> {
                    _states.update {
                        it.copy(
                            loginState = Resource.Error(
                                errorMessage = result.errors.errorMessage
                            )
                        )
                    }
                    _effects.send(LoginEffects.ShowToast(message = result.errors.errorMessage))

                }

                is ApiResult.SuccessApiResult<Unit> -> {
//todo: success
                    _states.update {
                        it.copy(loginState = Resource.Success(data = Unit))
                    }
//todo: show toast
                    _effects.send(LoginEffects.ShowToast(message = "Login Successfully"))
//todo: navigate to home screen
                    _effects.send(LoginEffects.OnNavigateToHome)
                }
            }
        }
    }


    private fun onSignUpClicked() {
        viewModelScope.launch {
//todo: navigate to register screen
            _effects.send(element = LoginEffects.OnNavigateToRegister)

        }
    }
}