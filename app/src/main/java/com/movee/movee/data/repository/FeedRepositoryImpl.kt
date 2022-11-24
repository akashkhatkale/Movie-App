package com.movee.movee.data.repository

import com.movee.movee.data.api.MoviesApi
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.repository.FeedRepository
import retrofit2.Response
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : FeedRepository {

    override suspend fun getPopularMovies(
        language: String,
        page: Int
    ): Response<MoviesResponseDto> {
        return moviesApi.getPopularMovies(language = language, page = page)
    }

    override suspend fun getTrendingToday(): Response<MoviesResponseDto> {
        return moviesApi.getTrendingToday()
    }

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): Response<MoviesResponseDto> {
        return moviesApi.getTopRatedMovies(language, page)
    }

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int
    ): Response<MoviesResponseDto> {
        return moviesApi.getUpcomingMovies(language, page)
    }
}
