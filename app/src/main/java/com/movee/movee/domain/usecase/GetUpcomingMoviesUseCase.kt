package com.movee.movee.domain.usecase

import com.movee.movee.commons.constants.ApiConstants.BASE_LANGUAGE
import com.movee.movee.data.dto.MoviesResponseDto
import retrofit2.Response

interface GetUpcomingMoviesUseCase : SuspendingUseCase<GetUpcomingMoviesUseCase.Input, Response<MoviesResponseDto>> {
    data class Input(
        val language: String = BASE_LANGUAGE,
        val page: Int = 1
    )
}