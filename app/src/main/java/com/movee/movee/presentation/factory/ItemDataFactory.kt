package com.movee.movee.presentation.factory

import androidx.compose.runtime.Composable
import com.movee.movee.commons.constants.UiConstants
import com.movee.movee.domain.entities.FeedItem
import com.movee.movee.domain.entities.HorizontalMoviesItem
import com.movee.movee.presentation.feature_feed.composables.HorizontalMovieItem

object ItemDataFactory {

    @Composable
    fun Create(feedItem: FeedItem) {
        when (feedItem.uiItemType) {
            UiConstants.HORIZONTAL_MOVIE_ITEM -> {
                HorizontalMovieItem(
                    feedItem = feedItem as HorizontalMoviesItem
                )
            }
        }
    }

}