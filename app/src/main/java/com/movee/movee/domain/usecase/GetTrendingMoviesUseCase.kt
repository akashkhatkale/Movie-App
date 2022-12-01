package com.movee.movee.domain.usecase

import com.movee.movee.data.api.Resource
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import retrofit2.Response

interface GetTrendingMoviesUseCase : SuspendingUseCase<Unit, Resource<MoviesResponse>>