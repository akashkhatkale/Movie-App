package com.movee.movee.domain.entities

import com.movee.movee.commons.extensions.empty


data class MoviesResponse(
    val page: Int = 0,
    val results: List<Result> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0,
    val dates: Date = Date()
) {
    data class Date(
        val maximum: String = "",
        val minimum: String = ""
    )

    data class Result(
        val adult: Boolean = false,
        val backdropPath: String = String.empty,
        val genreIds: List<Int> = emptyList(),
        val id: Int = 0,
        val originalLanguage: String = String.empty,
        val originalTitle: String = String.empty,
        val overview: String = String.empty,
        val popularity: Double = 0.0,
        val posterPath: String = String.empty,
        val releaseDate: String = String.empty,
        val title: String = String.empty,
        val vote: Double = 0.0
    )
}