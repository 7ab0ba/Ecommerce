package com.example.ecommerce.ui.theme.feature.auth.ui.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val coloreScheme = colorScheme
    val states by viewModel.states.collectAsStateWithLifecycle()


    var passwordVisible by remember { mutableStateOf(false) }
    var repasswordVisible by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf<String?>("") }
    var emailError by remember { mutableStateOf<String?>("") }

    var passwordError by remember { mutableStateOf<String?>("") }
    var repasswordError by remember { mutableStateOf<String?>("") }
    var phoneError by remember { mutableStateOf<String?>("") }

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effects ->
            when (effects) {
                RegisterEffects.OnNavigateToHome -> {
                    onSignUpClick()
                }

                is RegisterEffects.ShowToast -> {
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
            .verticalScroll(state = rememberScrollState())
            .background(color = coloreScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        TopLogoSection(
            modifier = modifier
                .size(width = 200.dp, height = 100.dp)
                .padding(top = 50.dp)
        )


        Text(
            text = "Full Name",
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            ),
        )
        CostumeTextField(
            value = states.name,
            onValueChange = { newvalue ->
                viewModel.onIntent(RegisterIntent.NameChanged(name = newvalue))
                nameError = null
            },
            modifier = modifier.fillMaxWidth(),

            supportingText = {
                nameError?.let {
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
            hinText = "Enter your Full name",
            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary,
            )

        )


        Text(
            text = "Mobile Number",
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            ),
        )
        CostumeTextField(
            value = states.phone,
            onValueChange = { newvalue ->
                viewModel.onIntent(RegisterIntent.PhoneChanged(phone = newvalue))
                phoneError = null
            },
            modifier = modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            supportingText = {
                phoneError?.let {
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
            hinText = "Mobile Number",
            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary,
            )
        )

        Text(
            text = "E-mail address",
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            ),
        )
        CostumeTextField(
            value = states.email,
            onValueChange = { newvalue ->
                viewModel.onIntent(RegisterIntent.EmailChanged(email = newvalue))
                emailError = null
            },
            modifier = modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

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
            hinText = "Enter your email address",
            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary,
            )
        )
        Text(
            text = "Password",
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            ),
        )
        CostumeTextField(
            value = states.password,
            onValueChange = { newvalue ->
                viewModel.onIntent(
                    RegisterIntent.PasswordChanged(password = newvalue)
                )
                passwordError = null
            },
            modifier = modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

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
                color = coloreScheme.tertiary,
            ),
            trailingIcon = {

                Icon(
                    if (passwordVisible) Icons.Default.Visibility else
                        Icons.Default.VisibilityOff,
                    contentDescription = "Visibility",
                    tint = coloreScheme.tertiary

                )
            }

        )
        Text(
            text = "Confirm Password",
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = coloreScheme.secondary
            ),
        )

        CostumeTextField(
            value = states.rePassword,
            onValueChange = { newvalue ->
                viewModel.onIntent(RegisterIntent.RePasswordChanged(rePassword = newvalue))
                repasswordError = null
            },
            modifier = modifier.fillMaxWidth(),

            colorScheme = coloreScheme,
            hinText = "Enter your Re-Password",
            hintStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = coloreScheme.tertiary,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (repasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            supportingText = {
                repasswordError?.let {
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
            trailingIcon = {

                Icon(
                    if (passwordVisible) Icons.Default.Visibility else
                        Icons.Default.VisibilityOff,
                    contentDescription = "Visibility",
                    tint = coloreScheme.tertiary

                )
            }

        )

        if (states.registerStates is Resource.Loading) {
            MainLoadingSection(color = colorScheme.secondary)
        } else {
            CustomElevatedButton(
                onClick = {

                    emailError = AppValidators.validateEmail(states.email)
                    passwordError = AppValidators.validatePassword(states.password)
                    phoneError = AppValidators.validatePhone(states.phone)
                    nameError = AppValidators.validateName(states.name)
                    repasswordError = AppValidators.validatePassword(states.rePassword)

                    if (emailError == null && passwordError == null && repasswordError == null && nameError == null && phoneError == null) {
                        //TODO:Register,Vavigate to HomeScreen
                        viewModel.onIntent(RegisterIntent.OnSignUpClick)
                    }
                },
                text = "Sign Up",
                coloreScheme = coloreScheme
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


    }

}

@Composable
fun TopLogoSection(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.logo_route_img),
        contentDescription = "Route Logo",
        modifier = modifier

    )
}

