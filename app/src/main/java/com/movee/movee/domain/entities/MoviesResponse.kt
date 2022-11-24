package com.movee.movee.domain.entities


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
        val backdropPath: String = "",
        val genreIds: List<Int> = emptyList(),
        val id: Int = 0,
        val originalLanguage: String = "",
        val originalTitle: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        val posterPath: String = "",
        val releaseDate: String = "",
        val title: String = "",
    )
}