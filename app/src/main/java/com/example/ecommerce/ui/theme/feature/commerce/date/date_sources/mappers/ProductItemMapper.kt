package com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.mappers

import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.network.models.response.product.ProductItemDto

fun ProductItemDto.toProductItem(): ProductItem {
    return ProductItem(

        sold = this.sold,
        images = this.images,
        quantity = this.quantity,
        availableColors = this.availableColors,
        imageCover = this.imageCover,
        description = this.description,
        title = this.title,
        ratingsQuantity = this.ratingsQuantity,
        ratingsAverage = this.ratingsAverage,
        price = this.price,
        priceAfterDiscount = this.priceAfterDiscount,
        id = this.id,
        brand = this.brand,
        category = this.category,
        subcategory = this.subcategory,
        slug = this.slug,
    )
}