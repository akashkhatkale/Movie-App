package com.movee.movee.domain.usecase

import com.movee.movee.commons.constants.ApiConstants.BASE_LANGUAGE
import com.movee.movee.data.api.Resource
import com.movee.movee.data.dto.MoviesResponseDto
import com.movee.movee.domain.entities.MoviesResponse
import retrofit2.Response

interface GetTopRatedMoviesUseCase : SuspendingUseCase<GetTopRatedMoviesUseCase.Input, Resource<MoviesResponse>> {
    data class Input(
        val language: String = BASE_LANGUAGE,
        val page: Int = 1
    )
}