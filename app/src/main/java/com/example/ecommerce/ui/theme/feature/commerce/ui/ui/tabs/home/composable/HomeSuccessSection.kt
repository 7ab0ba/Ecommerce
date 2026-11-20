package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.composable


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.common.CategoryItem
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainLoadingSection

@Composable
fun HomeSuccessSection(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    categorieslist: List<CategoryItem>
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(300.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(
            count = categorieslist.size
        ) { index ->
            ItemLayout(
                category = categorieslist[index],
                colorScheme = colorScheme
            )
        }
    }

}

@Composable
fun ItemLayout(
    modifier: Modifier = Modifier, category: CategoryItem, colorScheme: ColorScheme
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SubcomposeAsyncImage(
            model = category.image ?: "",
            loading = {
                MainLoadingSection()
            },
            contentDescription = "",
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            contentScale = ContentScale.Crop
        )



        Text(
            text = category.name ?: "",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorScheme.onPrimary
            )
        )
    }


}

