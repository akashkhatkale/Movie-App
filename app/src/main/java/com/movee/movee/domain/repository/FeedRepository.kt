package com.movee.movee.domain.repository

import com.movee.movee.data.dto.MoviesResponseDto
import retrofit2.Response

interface FeedRepository {
    suspend fun getPopularMovies(language: String, page: Int) : Response<MoviesResponseDto>
    suspend fun getTrendingToday() : Response<MoviesResponseDto>
    suspend fun getTopRatedMovies(language: String, page: Int) : Response<MoviesResponseDto>
    suspend fun getUpcomingMovies(language: String, page: Int) : Response<MoviesResponseDto>
}