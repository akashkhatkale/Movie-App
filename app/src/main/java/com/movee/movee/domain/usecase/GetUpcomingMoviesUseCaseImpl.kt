package com.movee.movee.domain.usecase

import com.movee.movee.data.api.Resource
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import com.movee.movee.domain.repository.FeedRepository
import retrofit2.Response
import javax.inject.Inject

class GetUpcomingMoviesUseCaseImpl @Inject constructor (
    private val repo : FeedRepository
) : GetUpcomingMoviesUseCase{
    override suspend fun execute(input: GetUpcomingMoviesUseCase.Input): Resource<MoviesResponse> {
        return repo.getUpcomingMovies(input.language, input.page)
    }
}