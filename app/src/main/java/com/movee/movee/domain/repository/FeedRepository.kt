package com.movee.movee.domain.repository

import com.movee.movee.data.api.Resource
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import retrofit2.Response

interface FeedRepository {
    suspend fun getPopularMovies(language: String, page: Int) : Resource<MoviesResponse>
    suspend fun getTrendingToday() : Resource<MoviesResponse>
    suspend fun getTopRatedMovies(language: String, page: Int) : Resource<MoviesResponse>
    suspend fun getUpcomingMovies(language: String, page: Int) : Resource<MoviesResponse>
}