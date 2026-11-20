package com.example.ecommerce.ui.theme.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.feature.auth.ui.login.LoginScreen
import com.example.ecommerce.ui.theme.feature.auth.ui.register.RegisterScreen
import com.example.ecommerce.ui.theme.feature.commerce.ui.main.MainTabScreen
import com.example.ecommerce.ui.theme.feature.splash.SplashScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier, startDestination: Any,
    onSplashFinished: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(MainTabRoute)
                },
                onSignUpClick = {
                    navController.navigate(RegisterRoute)
                },
                OnCreateAccountClicked = {
                    navController.navigate(RegisterRoute)
                }
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                onSignUpClick = {
                    navController.navigate(LoginRoute) {
                        popUpTo<RegisterRoute> {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<MainTabRoute> {
            MainTabScreen()
        }
        composable<SplashRoute> {
            SplashScreen(
                onSplashClick = {
                    onSplashFinished
                    navController.navigate(LoginRoute) {
                        popUpTo<SplashRoute> { inclusive = true }
                    }
                }
            )
        }
    }
}