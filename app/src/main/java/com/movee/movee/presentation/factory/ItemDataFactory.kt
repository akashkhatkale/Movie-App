package com.movee.movee.presentation.factory

import androidx.compose.runtime.Composable
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_CAROUSEL_MOVIE_ITEM
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_FULL_WIDTH_CAROUSEL_ITEM
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_MOVIE_ITEM
import com.movee.movee.domain.entities.FeedItem
import com.movee.movee.domain.entities.HorizontalCarouselMoviesFeedItem
import com.movee.movee.domain.entities.HorizontalFullWidthCarouselMoviesFeedItem
import com.movee.movee.domain.entities.HorizontalMoviesFeedItem
import com.movee.movee.presentation.composables.uiitemtype.HorizontalCarouselMovieItem
import com.movee.movee.presentation.composables.uiitemtype.HorizontalFullWidthCarouselItem
import com.movee.movee.presentation.feature_feed.composables.HorizontalMovieItem

object ItemDataFactory {

    @Composable
    fun Create(feedItem: FeedItem) {
        when (feedItem.uiItemType) {
            HORIZONTAL_MOVIE_ITEM -> HorizontalMovieItem(
                feedItem = feedItem as HorizontalMoviesFeedItem
            )

            HORIZONTAL_CAROUSEL_MOVIE_ITEM -> HorizontalCarouselMovieItem(
                feedItem = feedItem as HorizontalCarouselMoviesFeedItem
            )

            HORIZONTAL_FULL_WIDTH_CAROUSEL_ITEM -> HorizontalFullWidthCarouselItem(
                feedItem = feedItem as HorizontalFullWidthCarouselMoviesFeedItem
            )
        }
    }

}