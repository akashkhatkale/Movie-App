package com.movee.movee.domain.entities

data class MovieDetail(
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    data class SpokenLanguage(
        val englishName: String,
        val name: String
    )
}
