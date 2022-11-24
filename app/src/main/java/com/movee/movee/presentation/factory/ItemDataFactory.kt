package com.movee.movee.presentation.factory

import androidx.compose.runtime.Composable
import com.movee.movee.commons.constants.UiConstants
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_CAROUSEL_MOVIE_ITEM
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_MOVIE_ITEM
import com.movee.movee.domain.entities.FeedItem
import com.movee.movee.domain.entities.HorizontalCarouselMoviesItem
import com.movee.movee.domain.entities.HorizontalMoviesItem
import com.movee.movee.presentation.composables.uiitemtype.HorizontalCarouselMovieItem
import com.movee.movee.presentation.feature_feed.composables.HorizontalMovieItem

object ItemDataFactory {

    @Composable
    fun Create(feedItem: FeedItem) {
        when (feedItem.uiItemType) {
            HORIZONTAL_MOVIE_ITEM ->
                HorizontalMovieItem(
                    feedItem = feedItem as HorizontalMoviesItem
                )

            HORIZONTAL_CAROUSEL_MOVIE_ITEM ->
                HorizontalCarouselMovieItem(
                    feedItem = feedItem as HorizontalCarouselMoviesItem
                )

        }
    }

}