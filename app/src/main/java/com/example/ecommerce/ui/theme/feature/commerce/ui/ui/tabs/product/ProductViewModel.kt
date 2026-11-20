package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.use_cases.GetAllProductUseCase
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ProductStates(
    val productStates: Resource<List<ProductItem>> = Resource.Initial
)

sealed interface ProductIntent {
    data object LoadProduct : ProductIntent
    data object OnTryAgain : ProductIntent


    sealed interface ProductEffects {
        data class ShowToast(val message: String) : ProductEffects

        @HiltViewModel
        class ProductViewModel @Inject constructor(
            val getAllProductUseCase: GetAllProductUseCase
        ) : ViewModel() {

            private val _states: MutableStateFlow<ProductStates> = MutableStateFlow(ProductStates())
            val states = _states.asStateFlow()

            private val _effects: Channel<ProductEffects> = Channel(Channel.BUFFERED)
            val effects = _effects.receiveAsFlow()

            init {
                onIntent(LoadProduct)
            }

            fun onIntent(intent: ProductIntent) {
                when (intent) {
                    LoadProduct -> loadProduct()
                    OnTryAgain -> TODO()
                }
            }

            private fun loadProduct() {
                viewModelScope.launch {
//todo: loading
                    _states.update { it.copy(productStates = Resource.Loading) }
                    when (val result = getAllProductUseCase.invoke()) {
                        is ApiResult.ErrorApiResult -> {
//todo: error
                            _states.update {
                                it.copy(productStates = Resource.Error(errorMessage = result.errors.errorMessage))
                            }
//todo: show toast
                            _effects.send(element = ShowToast(message = result.errors.errorMessage))
                        }

                        is ApiResult.SuccessApiResult<List<ProductItem>> -> {
//todo: success
                            _states.update {
                                it.copy(
                                    productStates = Resource.Success(
                                        data = result.data ?: emptyList()
                                    )
                                )

                            }
                            //todo: show toast
                            _effects.send(ShowToast(message = "Product loading Successfullyl"))
                        }
                    }
                }


            }
        }
    }
}
