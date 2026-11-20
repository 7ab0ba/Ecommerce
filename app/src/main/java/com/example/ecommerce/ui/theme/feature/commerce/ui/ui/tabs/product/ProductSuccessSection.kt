package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem

@Composable
fun ProductSuccessSection(
    modifier: Modifier = Modifier,
    productList: List<ProductItem>,
    colorScheme: ColorScheme
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(
            count = productList.size,
            itemContent = { index ->
                ProductItem(
                    colorScheme = colorScheme,
                    product = productList[index]
                )
            }
        )
    }
}