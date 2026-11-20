package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.feature.commerce.domain.entities.product.ProductItem
import com.example.ecommerce.ui.theme.feature.commerce.ui.common.MainLoadingSection

//TODO : ProductItem (entity)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    product: ProductItem
) {
    Box(
        modifier = modifier
            .width(191.dp)
            .height(250.dp)
            .border(
                width = 2.dp,
                color = colorScheme.tertiary,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            PrductImgCoverSection(
                OnFavoritClick = {
                    // TODO: Add to favorite
                },
                colorScheme = colorScheme,
                image = product.imageCover ?: "" //Todo:Netwok
            )

            Text(
                text = product.title ?: "",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = colorScheme.onPrimary
                ),
                modifier = Modifier.padding(8.dp)
            )

            PriceSection(
                colorScheme = colorScheme,
                product = product
            )

            RateSection(
                colorScheme = colorScheme,
                OnAddToClick = {
                    //todo:add to cart
                },
                rating = product.ratingsAverage ?: "0.0"
            )
        }
    }
}

@Composable
fun PrductImgCoverSection(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    OnFavoritClick: () -> Unit,
    image: String
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        SubcomposeAsyncImage(
            model = image,
            loading = {
                MainLoadingSection()
            },
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp
                    )
                ),
            contentScale = ContentScale.FillBounds
        )

        IconButton(
            onClick = {
                OnFavoritClick()
            },
            modifier = Modifier.padding(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite icon",
                tint = colorScheme.primary,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorScheme.secondary)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun PriceSection(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    product: ProductItem
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp
            ),
        horizontalArrangement = Arrangement.Start
    ) {
        val displayPrice =
            product.priceAfterDiscount ?: product.price ?: 0.0

        Text(
            text = "EGP $displayPrice",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = colorScheme.onPrimary
            )
        )
        Spacer(modifier = modifier.width(12.dp))

        if (
            product.priceAfterDiscount != null &&
            product.price != null &&
            product.priceAfterDiscount != product.price
        ) {
            Text(
                text = "EGP ${product.price}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = colorScheme.onPrimary,
                    textDecoration = TextDecoration.LineThrough
                )
            )
        }
    }
}

@Composable
fun RateSection(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    OnAddToClick: () -> Unit,
    rating: Any
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 6.dp)

    ) {
        Text(
            text = "Review",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = colorScheme.onPrimary
            )
        )

        Text(
            text = "($rating)",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = colorScheme.onPrimary
            )
        )

        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = "Star icon"
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                OnAddToClick()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add to cart",
                tint = colorScheme.secondary,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorScheme.primary)
                    .padding(8.dp)
            )
        }
    }
}
