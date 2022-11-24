package com.movee.movee.presentation.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movee.movee.commons.constants.ApiConstants.BASE_LANGUAGE
import com.movee.movee.commons.constants.UiConstants.HORIZONTAL_MOVIE_ITEM
import com.movee.movee.commons.exceptions.NoInternetConnectionException
import com.movee.movee.data.api.Resource
import com.movee.movee.data.mapper.MoviesResponseMapper
import com.movee.movee.domain.entities.FeedItem
import com.movee.movee.domain.entities.HorizontalMoviesItem
import com.movee.movee.domain.usecase.GetPopularMoviesUseCase
import com.movee.movee.domain.usecase.GetTopRatedMoviesUseCase
import com.movee.movee.domain.usecase.GetTrendingMoviesUseCase
import com.movee.movee.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private var _language = BASE_LANGUAGE

    private var _feedItems = MutableStateFlow<Resource<ArrayList<FeedItem>>>(Resource.Idle())
    val feedItems = _feedItems.asStateFlow()

    init {
        loadFeed()
    }

    fun loadFeed() {
        viewModelScope.launch {
            _feedItems.emit(Resource.Loading())
            val feedList = arrayListOf<FeedItem>()
            try {
                val popularMovies = getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Input(_language))
                val trendingMovies = getTrendingMoviesUseCase.execute(Unit)
                val upcomingMovies = getUpcomingMoviesUseCase.execute(GetUpcomingMoviesUseCase.Input(_language))
                val topRatedMovies = getTopRatedMoviesUseCase.execute(GetTopRatedMoviesUseCase.Input(_language))

                popularMovies.body()?.let {
                    feedList.add(
                        HorizontalMoviesItem(
                            HORIZONTAL_MOVIE_ITEM,
                            "Popular Movies",
                            "",
                            MoviesResponseMapper.map(it)
                        )
                    )
                }
                trendingMovies.body()?.let {
                    feedList.add(
                        HorizontalMoviesItem(
                            HORIZONTAL_MOVIE_ITEM,
                            "Trending Movies",
                            "#trending today",
                            MoviesResponseMapper.map(it)
                        )
                    )
                }
                upcomingMovies.body()?.let {
                    feedList.add(
                        HorizontalMoviesItem(
                            HORIZONTAL_MOVIE_ITEM,
                            "Upcoming Movies",
                            "",
                            MoviesResponseMapper.map(it)
                        )
                    )
                }
                topRatedMovies.body()?.let {
                    feedList.add(
                        HorizontalMoviesItem(
                            HORIZONTAL_MOVIE_ITEM,
                            "Top Rated Movies",
                            "",
                            MoviesResponseMapper.map(it)
                        )
                    )
                }
                _feedItems.emit(Resource.Success(feedList))
            } catch (e: IOException) {
                _feedItems.emit(Resource.Error(NoInternetConnectionException()))
            } catch (e: HttpException) {
                _feedItems.emit(Resource.Error(com.movee.movee.commons.exceptions.HttpException(e.code())))
            }
        }
    }

    fun loadFeeed() {
//        viewModelScope.launch {
//            _feedItems.emit(Resource.Loading())
//            val feedList = arrayListOf<FeedItem>()
//            var failureMessage : Throwable? = null
//            val popularMovies = async {
//                getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Input(_language))
//            }.await().onSuccess {
//                feedList.add(PopularMoviesItem(POPULAR_MOVIE_ITEM, "Popular Movies", "", it))
//            }.onFailure {
//                failureMessage = it
//            }
//            val trendingMovies = async {
//                getTrendingMoviesUseCase.execute(Unit)
//            }.await().onSuccess {
//                feedList.add(TrendingMoviesItem(TRENDING_MOVIE_ITEM, "Trending Movies", "", it))
//            }.onFailure {
//                failureMessage = it
//            }
//            val topRatedMovies = async {
//                getTopRatedMoviesUseCase.execute(GetTopRatedMoviesUseCase.Input(_language))
//            }.await().onSuccess {
//                feedList.add(TopRatedMoviesItem(TOPRATED_MOVIE_ITEM, "Top Rated Movies", "", it))
//            }.onFailure {
//                failureMessage = it
//            }
//            val upcomingMovies = async {
//                getUpcomingMoviesUseCase.execute(GetUpcomingMoviesUseCase.Input(_language))
//            }.await().onSuccess {
//                feedList.add(UpcomingMoviesItem(UPCOMING_MOVIE_ITEM, "Upcoming Movies", "", it))
//            }.onFailure {
//                failureMessage = it
//            }
//            if (popularMovies.isSuccess && trendingMovies.isSuccess && topRatedMovies.isSuccess && upcomingMovies.isSuccess) {
//                _feedItems.emit(Resource.Success(feedList))
//            } else if (failureMessage != null) {
//                when (failureMessage) {
//                    is HttpException -> {
//                    }
//                    is IOException -> {
//                        _feedItems.emit(Resource.Error(com.movee.movee.commons.exceptions.NoInternetConnectionException()))
//                    }
//                    else -> {
//                        _feedItems.emit(Resource.Error(com.movee.movee.commons.exceptions.UnknownException()))
//                    }
//                }
//            }
//        }
    }

    fun done() {}
}