package com.example.ecommerce.ui.theme.feature.commerce.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.components.CostumeTextField
import com.example.ecommerce.ui.theme.feature.commerce.ui.components.CustomBottomBar
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.favorite.FavoriteTab
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.HomeTab
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.product.ProductTab
import com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.profile.ProfileTab


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTabScreen(modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    var selectedIndex by remember { mutableStateOf(0) }
    var Searchvalue by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            CustomBottomBar(
                colorScheme = colorScheme,
                selectedindex = selectedIndex,
                onTabClick = { index ->
                    selectedIndex = index
                }

            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Route Logo",
                        modifier = Modifier
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SearchTextLayOut(
                Searchvalue = Searchvalue,
                onValueChange = { newvalue ->
                    Searchvalue = newvalue
                },
                colorScheme = colorScheme,
                hintText = "what do you search for?",
                hintStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = colorScheme.tertiary
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                when (selectedIndex) {
                    0 -> {
                        HomeTab()
                    }

                    1 -> {
                        ProductTab()
                    }

                    2 -> {
                        FavoriteTab()
                    }

                    3 -> {
                        ProfileTab()
                    }
                }


            }
        }
    }
}


@Composable
fun SearchTextLayOut(
    modifier: Modifier = Modifier,
    Searchvalue: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    hintStyle: TextStyle,
    colorScheme: ColorScheme
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        CostumeTextField(
            value = Searchvalue,
            onValueChange = onValueChange,
            hinText = hintText,
            hintStyle = hintStyle,
            colorScheme = colorScheme,
            modifier = modifier.weight(1f),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.search_),
                    contentDescription = "Search icon",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            },
            size = 25.dp
        )
        Image(
            painter = painterResource(R.drawable.shopping_cart_),
            contentDescription = "Cart icon",
        )
    }

}
