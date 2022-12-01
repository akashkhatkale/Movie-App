package com.movee.movee.presentation.feature_feed.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.movee.movee.commons.composables.MovieCard
import com.movee.movee.commons.constants.MovieCardSizeConstants
import com.movee.movee.domain.entities.MoviesResponse

@Preview
@Composable
fun HorizontalCarouselMoviesList(
    movies: List<MoviesResponse.Result> = emptyList(),
    marginTop: Dp = 10.dp
) {
    LazyRow {
        items(movies) { movie ->
            Spacer(modifier = Modifier.width(marginTop))
            MovieCard(
                movie.backdropPath,
                MovieCardSizeConstants.CAROUSEL
            )
        }
    }
}