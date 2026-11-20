package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainErrorSection
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainLoadingSection
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.composable.AdsSectionLayOut
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.composable.HomeSuccessSection
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.composable.MainSectionLayOut

@Composable
fun HomeTab(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme

    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = 16.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                AdsSectionLayOut()

                // Categories Section
                MainSectionLayOut(
                    Categories = "Categories",
                    viewAll = "View all",
                    colorScheme = colorScheme
                )

                when (val categoryState = state.categoryState) {

                    is Resource.Error -> {
                        MainErrorSection(
                            onTryAgain = {
                                viewModel.loadCategory()
                            },
                            errormessage = categoryState.errorMessage
                        )
                    }

                    Resource.Initial -> {
                        MainLoadingSection()
                    }

                    Resource.Loading -> {
                        MainLoadingSection()
                    }

                    is Resource.Success -> {
                        HomeSuccessSection(
                            categorieslist = categoryState.data,
                            colorScheme = colorScheme
                        )
                    }
                }

                // Brands Section
                MainSectionLayOut(
                    Categories = "Home Appliance",
                    viewAll = "View all",
                    colorScheme = colorScheme
                )

                when (val brandState = state.brandState) {

                    is Resource.Error -> {
                        MainErrorSection(
                            onTryAgain = {
                                viewModel.LoadBrands()
                            },
                            errormessage = brandState.errorMessage
                        )
                    }

                    Resource.Initial -> {
                        MainLoadingSection()
                    }

                    Resource.Loading -> {
                        MainLoadingSection()
                    }

                    is Resource.Success -> {
                        HomeSuccessSection(
                            categorieslist = brandState.data,
                            colorScheme = colorScheme
                        )
                    }
                }
            }
        }
    }
}