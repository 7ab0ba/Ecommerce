package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.theme.core.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Initialize MainViewModel using the Activity ViewModel delegate
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        setContent {
            // Apply the app theme
            EcommerceTheme {

                // Observe the start destination from MainViewModel
                val startDestination =
                    mainViewModel.startDestination.collectAsStateWithLifecycle().value

                // Navigate only after the start destination is determined
                if (startDestination != null) {
                    AppNavigation(
                        startDestination = startDestination,
                        onSplashFinished = { mainViewModel.onSplashFinished() }
                    )
                }
            }
        }


//        var module :NetworkModule=NetworkModule()
//        val okHttpClient=module.provideOkHttpClient()
//        val retrofit =module.provideRetrofit(okHttpClient)
//        val apiServices =module.provieApiServices(retrofit)
//lifecycleScope.launch { apiServices.login(
//LoginRequestDto(
//    email = "route_sun_c21@gmail.com",
//    password = "Route@123"
//))
//}
    }
}