package com.movee.movee.domain.usecase

import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.repository.FeedRepository
import retrofit2.Response
import javax.inject.Inject

class GetTrendingMoviesUseCaseImpl @Inject constructor (
    private val repo : FeedRepository
) : GetTrendingMoviesUseCase{

    override suspend fun execute(input: Unit): Response<MoviesResponseDto> {
        return repo.getTrendingToday()
    }

}