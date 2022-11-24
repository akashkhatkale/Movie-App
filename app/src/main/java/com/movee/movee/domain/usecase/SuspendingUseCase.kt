package com.movee.movee.domain.usecase

interface SuspendingUseCase<in I, out O> {
    suspend fun execute(input: I) : O
}