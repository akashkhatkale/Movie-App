package com.movee.movee.data.mapper

import com.movee.movee.commons.extensions.orZero
import com.movee.movee.data.dto.MovieDetailDto
import com.movee.movee.domain.entities.MovieDetail
import com.movee.movee.domain.mapper.Mapper

class MovieDetailMapper: Mapper<MovieDetailDto, MovieDetail> {
    override fun map(input: MovieDetailDto): MovieDetail {
        return MovieDetail(
            backdropPath = input.backdropPath.orEmpty(),
            budget = input.budget.orZero(),
            runtime = input.runtime.orZero(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            popularity = input.popularity.orZero(),
            voteAverage = input.voteAverage.orZero(),
            voteCount = input.voteCount.orZero(),
            id = input.id.orZero(),
            status = input.status.orEmpty(),
            revenue = input.revenue.orZero(),
            originalLanguage = input.originalLanguage.orEmpty(),
            originalTitle = input.originalTitle.orEmpty(),
            genres = input.genres?.map {
                MovieDetail.Genre(
                    it.id.orZero(),
                    it.name.orEmpty()
                )
            }.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            spokenLanguages = input.spokenLanguages?.map {
                MovieDetail.SpokenLanguage(
                    it.englishName.orEmpty(),
                    it.name.orEmpty()
                )
            }.orEmpty(),
            homepage = input.homepage.orEmpty(),
            posterPath = input.posterPath.orEmpty()
        )
    }
}