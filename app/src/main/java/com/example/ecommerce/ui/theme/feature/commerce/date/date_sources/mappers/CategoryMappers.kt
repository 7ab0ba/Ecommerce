package com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.mappers

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.network.models.response.common.CategoryItemDto

fun CategoryItemDto.toCategory(): CategoryItem {
    return CategoryItem(
        image = this.image,
        name = this.name,
        id = this.id,
        category = this.category
    )
}