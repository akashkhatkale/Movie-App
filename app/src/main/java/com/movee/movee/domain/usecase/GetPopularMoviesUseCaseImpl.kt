package com.movee.movee.domain.usecase

import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import com.movee.movee.domain.repository.FeedRepository
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor (
    private val repo : FeedRepository
) : GetPopularMoviesUseCase{
    override suspend fun execute(input: GetPopularMoviesUseCase.Input): Response<MoviesResponseDto> {
        return repo.getPopularMovies(input.language, input.page)
    }
}