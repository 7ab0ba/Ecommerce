package com.example.ecommerce.ui.theme.feature.auth.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.ui.theme.feature.auth.domain.usecase.RegisterUseCase
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.network.models.request.auth.register.RegisterRequest
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// States
data class RegisterStates(
    val name: String = "Habiba",
    val email: String = "habiba@fue.com",
    val phone: String = "01000000000",
    val password: String = "12345678@Ha",
    val rePassword: String = "12345678@Ha",
    val registerStates: Resource<Unit> = Resource.Initial
)

// Intents
sealed interface RegisterIntent {
    data class EmailChanged(val email: String) : RegisterIntent
    data class NameChanged(val name: String) : RegisterIntent
    data class PhoneChanged(val phone: String) : RegisterIntent
    data class PasswordChanged(val password: String) : RegisterIntent
    data class RePasswordChanged(val rePassword: String) : RegisterIntent
    data object OnRegisterClicked : RegisterIntent
    data object OnSignUpClick : RegisterIntent
}

// Effects
sealed interface RegisterEffects {
    data class ShowToast(val message: String) : RegisterEffects
    data object OnNavigateToHome : RegisterEffects
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _states: MutableStateFlow<RegisterStates> =
        MutableStateFlow(value = RegisterStates())

    val states = _states.asStateFlow()

    private val _effects: Channel<RegisterEffects> =
        Channel(capacity = Channel.BUFFERED)

    val effects: Flow<RegisterEffects> = _effects.receiveAsFlow()

    fun onIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.EmailChanged ->
                changedEmail(email = intent.email)

            is RegisterIntent.NameChanged ->
                changedName(name = intent.name)

            RegisterIntent.OnRegisterClicked,
            RegisterIntent.OnSignUpClick ->
                register()

            is RegisterIntent.PasswordChanged ->
                changedPassword(password = intent.password)

            is RegisterIntent.PhoneChanged ->
                changedPhone(phone = intent.phone)

            is RegisterIntent.RePasswordChanged ->
                changedRePassword(rePassword = intent.rePassword)
        }
    }

    private fun changedEmail(email: String) {
        _states.update { it.copy(email = email) }
    }

    private fun changedName(name: String) {
        _states.update { it.copy(name = name) }
    }

    private fun changedPhone(phone: String) {
        _states.update { it.copy(phone = phone) }
    }

    private fun changedPassword(password: String) {
        _states.update { it.copy(password = password) }
    }

    private fun changedRePassword(rePassword: String) {
        _states.update { it.copy(rePassword = rePassword) }
    }

    private fun register() {
        viewModelScope.launch {
            _states.update {
                it.copy(registerStates = Resource.Loading)
            }

            when (
                val result = registerUseCase.invoke(
                    registerRequest = RegisterRequest(
                        name = _states.value.name,
                        email = _states.value.email,
                        phone = _states.value.phone,
                        password = _states.value.password,
                        rePassword = _states.value.rePassword
                    )
                )
            ) {
                is ApiResult.ErrorApiResult -> {
                    _states.update {
                        it.copy(
                            registerStates = Resource.Error(
                                errorMessage = result.errors.errorMessage
                            )
                        )
                    }

                    _effects.send(
                        RegisterEffects.ShowToast(
                            message = result.errors.errorMessage
                        )
                    )
                }

                is ApiResult.SuccessApiResult<Unit> -> {
                    _states.update {
                        it.copy(
                            registerStates = Resource.Success(data = Unit)
                        )
                    }

                    _effects.send(
                        RegisterEffects.ShowToast(
                            message = "Register Successfully"
                        )
                    )

                    _effects.send(RegisterEffects.OnNavigateToHome)
                }
            }
        }
    }
}
