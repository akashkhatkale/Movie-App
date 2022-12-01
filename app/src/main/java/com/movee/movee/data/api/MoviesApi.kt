package com.movee.movee.data.api

import com.movee.movee.commons.constants.ApiConstants.BASE_LANGUAGE
import com.movee.movee.commons.constants.ApiConstants.BASE_VERSION
import com.movee.movee.data.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path


interface MoviesApi {

    // get popular movies
    @GET("$BASE_VERSION/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = BASE_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MoviesResponseDto>

    // get trending today
    @GET("$BASE_VERSION/trending/all/day")
    suspend fun getTrendingToday(): Response<MoviesResponseDto>

    // get top movies
    @GET("$BASE_VERSION/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = BASE_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MoviesResponseDto>

    // get upcoming movies
    @GET("$BASE_VERSION/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = BASE_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MoviesResponseDto>

    // get top tv shows

    // get movie details
    @GET("$BASE_VERSION/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("language") language: String = BASE_LANGUAGE
    )

    // get recommended movies/tv

    // get search results

}