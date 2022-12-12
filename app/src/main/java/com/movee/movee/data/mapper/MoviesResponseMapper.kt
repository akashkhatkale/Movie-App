package com.movee.movee.data.mapper

import com.movee.movee.commons.extensions.orFalse
import com.movee.movee.commons.extensions.orZero
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import com.movee.movee.domain.mapper.Mapper

object MoviesResponseMapper : Mapper<MoviesResponseDto, MoviesResponse> {
    override fun map(input: MoviesResponseDto): MoviesResponse =
        MoviesResponse(
            input.page.orZero(),
            input.results?.map {
                MoviesResponse.Result(
                    it.adult.orFalse(),
                    it.backdropPath.orEmpty(),
                    it.genreIds ?: emptyList(),
                    it.id.orZero(),
                    it.originalLanguage.orEmpty(),
                    it.originalTitle.orEmpty(),
                    it.overview.orEmpty(),
                    it.popularity.orZero(),
                    it.posterPath.orEmpty(),
                    it.releaseDate.orEmpty(),
                    it.title.orEmpty(),
                    it.voteAverage.orZero()
                )
            } ?: emptyList(),
            input.totalPages.orZero(),
            input.totalResults.orZero(),
            MoviesResponse.Date(
                input.dates?.maximum.orEmpty(),
                input.dates?.minimum.orEmpty()
            )
        )
}