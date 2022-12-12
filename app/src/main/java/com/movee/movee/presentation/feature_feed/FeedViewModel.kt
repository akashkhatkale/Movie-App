package com.movee.movee.presentation.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movee.movee.commons.constants.ApiConstants.BASE_LANGUAGE
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_CAROUSEL_MOVIE_ITEM
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_FULL_WIDTH_CAROUSEL_ITEM
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_MOVIE_ITEM
import com.movee.movee.commons.exceptions.UnknownException
import com.movee.movee.commons.extensions.empty
import com.movee.movee.data.api.Resource
import com.movee.movee.domain.entities.*
import com.movee.movee.domain.usecase.GetPopularMoviesUseCase
import com.movee.movee.domain.usecase.GetTopRatedMoviesUseCase
import com.movee.movee.domain.usecase.GetTrendingMoviesUseCase
import com.movee.movee.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private var _language = BASE_LANGUAGE

    private var _feedItems = MutableStateFlow<Resource<List<FeedItem>>>(Resource.Idle())
    val feedItems = _feedItems.asStateFlow()

    init {
        fetchFeed()
    }

    private fun fetchFeed() {
        viewModelScope.launch {
            _feedItems.emit(Resource.Loading())
            val popularMovies = getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Input(_language))
            val trendingMovies = getTrendingMoviesUseCase.execute(Unit)
            val upcomingMovies = getUpcomingMoviesUseCase.execute(GetUpcomingMoviesUseCase.Input(_language))
            val topRatedMovies = getTopRatedMoviesUseCase.execute(GetTopRatedMoviesUseCase.Input(_language))

            val (feedList, exception) = buildFeedList(
                linkedMapOf(
                    FeedItem(
                        HORIZONTAL_FULL_WIDTH_CAROUSEL_ITEM,
                        "Popular Movies",
                        "",
                    ) to popularMovies,

                    FeedItem(
                        HORIZONTAL_MOVIE_ITEM,
                        "Trending Movies",
                        "#trending today",
                    ) to trendingMovies,

                    FeedItem(
                        HORIZONTAL_MOVIE_ITEM,
                        "Upcoming Movies",
                        "",
                    ) to upcomingMovies,

                    FeedItem(
                        HORIZONTAL_MOVIE_ITEM,
                        "Top Rated Movies",
                        "",
                    ) to topRatedMovies,
                )
            )
            if (feedList.isNotEmpty()){
                _feedItems.emit(Resource.Success(feedList))
            } else {
                exception?.let {
                    _feedItems.emit(Resource.Error(it))
                } ?: run {
                    _feedItems.emit(Resource.Error(UnknownException()))
                }
            }
        }
    }

    private fun <T> getResponseResult(response: Resource<T>) : Pair<T?, Exception?> {
        return when (response) {
            is Resource.Success -> {
                Pair(response.data, null)
            }
            else -> Pair(null, response.message)
        }
    }

    private fun buildFeedList(responseList: LinkedHashMap<FeedItem, Resource<MoviesResponse>>): Pair<List<FeedItem>, Exception?> {
        var error: Exception? = null
        val feedList = arrayListOf<FeedItem>()

        responseList.forEach { mapEntry ->
            val (response, exception) = getResponseResult(mapEntry.value)
            response?.let {
                feedList.add(
                    getFeedItemType(mapEntry.key, it)
                )
            }
            exception?.let {
                error = it
            }
        }

        return Pair(feedList, error)
    }

    private fun getFeedItemType(feedItem: FeedItem, response: MoviesResponse): FeedItem =
        when (feedItem.uiItemType) {
            HORIZONTAL_MOVIE_ITEM -> {
                HorizontalMoviesFeedItem(
                    feedItem.uiItemType,
                    feedItem.title,
                    feedItem.subtitle,
                    response
                )
            }
            HORIZONTAL_CAROUSEL_MOVIE_ITEM -> {
                HorizontalCarouselMoviesFeedItem(
                    feedItem.uiItemType,
                    feedItem.title,
                    feedItem.subtitle,
                    response
                )
            }
            HORIZONTAL_FULL_WIDTH_CAROUSEL_ITEM -> {
                HorizontalFullWidthCarouselMoviesFeedItem(
                    feedItem.uiItemType,
                    feedItem.title,
                    feedItem.subtitle,
                    response
                )
            }
            else -> FeedItem(String.empty, String.empty, String.empty)
        }

}