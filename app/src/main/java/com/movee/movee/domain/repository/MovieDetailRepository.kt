package com.movee.movee.domain.repository

import com.movee.movee.data.dto.MovieDetailDto
import retrofit2.Response

interface MovieDetailRepository {

    suspend fun getMovieDetails(movieId: String): Response<MovieDetailDto>

}