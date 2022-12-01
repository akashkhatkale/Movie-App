package com.movee.movee.data.repository

import com.movee.movee.data.api.MoviesApi
import com.movee.movee.data.api.Resource
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.data.mapper.MoviesResponseMapper
import com.movee.movee.domain.entities.MoviesResponse
import com.movee.movee.domain.repository.FeedRepository
import retrofit2.Response
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : FeedRepository, BaseRepository() {

    override suspend fun getPopularMovies(
        language: String,
        page: Int
    ): Resource<MoviesResponse> {
        return safeApiCall({ moviesApi.getPopularMovies(language = language, page = page)}, MoviesResponseMapper)
    }

    override suspend fun getTrendingToday(): Resource<MoviesResponse> {
        return safeApiCall({moviesApi.getTrendingToday()}, MoviesResponseMapper)
    }

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): Resource<MoviesResponse> {
        return safeApiCall({moviesApi.getTopRatedMovies(language, page)}, MoviesResponseMapper)
    }

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int
    ): Resource<MoviesResponse> {
        return safeApiCall({moviesApi.getUpcomingMovies(language, page)}, MoviesResponseMapper)
    }
}
