package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.use_cases.GetAllBrandsUseCase
import com.example.ecommerce.ui.theme.feature.commerce.domain.use_cases.GetAllCategoryUseCase
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.network.utility.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getAllCategoryUseCase: GetAllCategoryUseCase,
    val getALLBranUseCase: GetAllBrandsUseCase
) : ViewModel() {

    // todo: states (initial, loading, success, error)
    private val _states: MutableStateFlow<HomeStates> =
        MutableStateFlow(value = HomeStates())

    val state: StateFlow<HomeStates> = _states.asStateFlow()

    init {
        loadCategory()
        LoadBrands()
    }

    fun loadCategory() {
        viewModelScope.launch {
            _states.value = _states.value.copy(
                categoryState = Resource.Loading
            )

            when (val result = getAllCategoryUseCase.invoke()) {
                is ApiResult.ErrorApiResult -> {
                    _states.value = _states.value.copy(
                        categoryState = Resource.Error(
                            errorMessage = result.errors.errorMessage
                        )
                    )
                }

                is ApiResult.SuccessApiResult<List<CategoryItem>> -> {
                    _states.value = _states.value.copy(
                        categoryState = Resource.Success(
                            data = result.data!!
                        )
                    )
                }
            }
        }
    }

    fun LoadBrands() {
        viewModelScope.launch {
            // todo: Loading
            _states.value = _states.value.copy(
                brandState = Resource.Loading
            )

            when (val result = getALLBranUseCase.invoke()) {
                is ApiResult.ErrorApiResult -> {
                    _states.value = _states.value.copy(
                        brandState = Resource.Error(
                            errorMessage = result.errors.errorMessage
                        )
                    )
                }

                is ApiResult.SuccessApiResult -> {
                    _states.value = _states.value.copy(
                        brandState = Resource.Success(
                            data = result.data!!
                        )
                    )
                }
            }
        }
    }
}
