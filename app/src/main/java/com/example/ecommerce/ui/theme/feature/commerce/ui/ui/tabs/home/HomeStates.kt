package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.domain.utility.Resource

data class HomeStates(
    val categoryState: Resource<List<CategoryItem>> = Resource.Initial,
    val brandState: Resource<List<CategoryItem>> = Resource.Initial,
)