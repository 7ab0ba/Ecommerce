package com.example.ecommerce.ui.theme.feature.auth.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.components.CostumeTextField
import com.example.ecommerce.ui.theme.components.CustomElevatedButton
import com.example.ecommerce.ui.theme.core.utils.AppValidators
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainLoadingSection

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    OnCreateAccountClicked: () -> Unit,

    viewModel: LoginViewModel = hiltViewModel()
) {

    val coloreScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()
    val state by viewModel.states.collectAsStateWithLifecycle()
    val context = LocalContext.current


    var emailError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effects ->
            when (effects) {
                LoginEffects.OnNavigateToHome -> {
                    onLoginClick()
                }

                LoginEffects.OnNavigateToRegister -> {
                    OnCreateAccountClicked()
                }

                is LoginEffects.ShowToast -> {
                    Toast.makeText(
                        context, effects.message,

                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = coloreScheme.primary)
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        TopLogoSection(
            modifier = Modifier
                .size(width = 200.dp, height = 100.dp)
                .padding(top = 50.dp)
        )

        Text(
            text = "Welcome Back To Route",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = coloreScheme.secondary
            )
        )

        Text(
            text = "Please sign in with your mail",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.secondary
            )
        )

        Text(
            text = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            )
        )

        CostumeTextField(
            value = state.email,
            onValueChange = { newValue ->
                viewModel.onIntent(intent = LoginIntent.EmailChanged(email = newValue))
                emailError = null
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = modifier.fillMaxWidth(),

            supportingText = {
                emailError?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            color = coloreScheme.error
                        )
                    )
                }
            },

            colorScheme = coloreScheme,
            hinText = "Enter your email",

            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary
            )
        )

        Text(
            text = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            )
        )

        CostumeTextField(
            value = state.password,
            onValueChange = { newValue ->
                viewModel.onIntent(LoginIntent.PasswordChanged(password = newValue))
                passwordError = null
            },
            modifier = modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            visualTransformation =
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

            supportingText = {
                passwordError?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            color = coloreScheme.error
                        )
                    )
                }
            },

            colorScheme = coloreScheme,
            hinText = "Enter your Password",

            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary
            ),

            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (passwordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },

                        contentDescription = "Toggle Password Visibility",
                        tint = coloreScheme.tertiary
                    )
                }
            }
        )

        Text(
            text = "Forgot Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                color = coloreScheme.secondary
            ),
            textAlign = TextAlign.End
        )


        if (state.loginState is Resource.Loading) {
            MainLoadingSection(color = coloreScheme.secondary)
        } else {
            CustomElevatedButton(
                onClick = {

                    emailError = AppValidators.validateEmail(state.email)

                    passwordError =
                        AppValidators.validatePassword(state.password)

                    if (emailError == null && passwordError == null) {

                        // Navigate to HomeScreen
                        viewModel.onIntent(LoginIntent.OnLoginClicked)
                    }
                },

                text = "Login",
                coloreScheme = coloreScheme
            )

        }

        EndSection(
            text1 = "Don’t have an account?",
            text2 = "Create Account",
            coloreScheme = coloreScheme,
            oncreateaccountclick = {
                viewModel.onIntent(LoginIntent.OnCreateAccountClicked)
            }
        )
    }
}

@Composable
fun TopLogoSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.logo_route_img),
        contentDescription = "Route Logo",
        modifier = modifier
    )
}

@Composable
fun EndSection(
    modifier: Modifier = Modifier,
    coloreScheme: ColorScheme,
    text1: String,
    text2: String,
    oncreateaccountclick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),

        horizontalArrangement = Arrangement.Center,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text1,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            )
        )

        TextButton(
            onClick = oncreateaccountclick
        ) {
            Text(
                text = text2,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = coloreScheme.secondary
                )
            )
        }
    }
}
