package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.product

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainErrorSection
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainLoadingSection

//TODO :List<Products>
@Composable
fun ProductTab(
    modifier: Modifier = Modifier,
    viewModel: ProductIntent.ProductEffects.ProductViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    val states = viewModel.states.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effects ->
            when (effects) {
                is ProductIntent.ProductEffects.ShowToast -> {
                    Toast.makeText(
                        context,
                        effects.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    when (val productState = states.value.productStates) {

        is Resource.Error -> MainErrorSection(
            errormessage = productState.errorMessage,
            onTryAgain = {
                //todo: try again
            }
        )

        Resource.Initial, Resource.Loading -> MainLoadingSection()

        is Resource.Success<List<ProductItem>> -> {
            val productList = productState.data

            if (productList.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Product Item Found",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorScheme.primary
                        )
                    )
                }
            } else {
                ProductSuccessSection(
                    productList = productList,
                    colorScheme = colorScheme
                )
            }
        }
    }
}
