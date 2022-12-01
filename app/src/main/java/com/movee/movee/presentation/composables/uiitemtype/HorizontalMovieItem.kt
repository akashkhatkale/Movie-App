package com.movee.movee.presentation.feature_feed.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movee.movee.commons.composables.HorizontalMoviesList
import com.movee.movee.domain.entities.HorizontalMoviesItem
import com.movee.movee.presentation.theme.SubtitleColor
import com.movee.movee.presentation.theme.TextColor

@Composable
fun HorizontalMovieItem(
    feedItem: HorizontalMoviesItem
) {
    Column {
        Text(
            feedItem.title,
            style = TextStyle(
                color = TextColor,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(start = 15.dp)
        )
        if (feedItem.subtitle.isNotEmpty()) {
            Text(
                feedItem.subtitle,
                style = TextStyle(
                    color = SubtitleColor,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 15.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalMoviesList(feedItem.response.results)
        Spacer(modifier = Modifier.height(20.dp))
    }
}