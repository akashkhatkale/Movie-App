@file:Suppress("ControlFlowWithEmptyBody")

package com.movee.movee.presentation.composables.uiitemtype

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.movee.movee.commons.composables.BigTitle
import com.movee.movee.commons.composables.HorizontalFullWidthCarouselListItem
import com.movee.movee.commons.constants.ApiConstants
import com.movee.movee.commons.extensions.getImageUrl
import com.movee.movee.commons.extensions.getTrimmedList
import com.movee.movee.domain.entities.HorizontalFullWidthCarouselMoviesFeedItem
import com.movee.movee.domain.entities.MoviesResponse
import kotlinx.coroutines.delay

private const val AUTO_SCROLL_DELAY = 5000L

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalFullWidthCarouselItem(
    feedItem: HorizontalFullWidthCarouselMoviesFeedItem
) {
    val pagerState = rememberPagerState()
    val trimmedFeedItem = feedItem.response.results.getTrimmedList(6)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalFullWidthCarouselList(
            trimmedFeedItem,
            pagerState,
        )
        Spacer(modifier = Modifier.height(50.dp))
        DotsIndicator(current = pagerState.currentPage, total = pagerState.pageCount)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalFullWidthCarouselList(
    list: List<MoviesResponse.Result> = emptyList(),
    pagerState: PagerState
) {
    LaunchedEffect(Unit) {
        while (true) {
            // perform auto scrolling
            delay(AUTO_SCROLL_DELAY)
            with(pagerState) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

                animateScrollToPage(
                    page = target,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = androidx.compose.animation.core.FastOutSlowInEasing
                    )
                )
            }
        }
    }

    HorizontalPager(
        count = list.size,
        state = pagerState
    ) { currentPage ->
        val currentItem = list[currentPage]
        HorizontalFullWidthCarouselListItem(
            item = currentItem,
        )
    }
}


@Composable
fun DotsIndicator(current: Int, total: Int) {
    LazyRow {
        items(total) {
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (current == it) Color.White else MaterialTheme.colors.secondary)
            )

            if (it != total - 1) {
                Spacer(
                    modifier = Modifier.width(5.dp)
                )
            }
        }
    }
}

