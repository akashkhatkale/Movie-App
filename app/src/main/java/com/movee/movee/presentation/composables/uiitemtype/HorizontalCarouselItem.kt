package com.movee.movee.presentation.composables.uiitemtype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.movee.movee.domain.entities.HorizontalCarouselMoviesFeedItem
import com.movee.movee.presentation.feature_feed.composables.HorizontalCarouselMoviesList

@Composable
fun HorizontalCarouselMovieItem(
    feedItem: HorizontalCarouselMoviesFeedItem
) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalCarouselMoviesList(feedItem.response.results)
        Spacer(modifier = Modifier.height(20.dp))
    }
}