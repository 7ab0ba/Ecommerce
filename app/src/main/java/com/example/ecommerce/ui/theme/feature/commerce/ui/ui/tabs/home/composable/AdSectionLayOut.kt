package com.example.ecommerce.ui.theme.feature.commerce.ui.ui.tabs.home.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R
import kotlinx.coroutines.delay


@Composable
fun AdsSectionLayOut(
    modifier: Modifier = Modifier
) {
    val adsImagesList = listOf(
        R.drawable.ads,
        R.drawable.ads2,
        R.drawable.ads3
    )

    val pagerState = rememberPagerState(
        initialPage = 3000,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)

            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(
                    durationMillis = 1000
                )
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        pageSpacing = 14.dp,
        beyondViewportPageCount = 1
    ) { index ->

        val imageIndex = index % adsImagesList.size

        Image(
            painter = painterResource(adsImagesList[imageIndex]),
            contentDescription = "Advertisement",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}
