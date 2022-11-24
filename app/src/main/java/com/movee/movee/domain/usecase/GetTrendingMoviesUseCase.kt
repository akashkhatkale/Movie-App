package com.movee.movee.domain.usecase

import com.movee.movee.data.dto.MoviesResponseDto
import retrofit2.Response

interface GetTrendingMoviesUseCase : SuspendingUseCase<Unit, Response<MoviesResponseDto>>