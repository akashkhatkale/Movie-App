package com.movee.movee.commons.di

import com.movee.movee.data.repository.FeedRepositoryImpl
import com.movee.movee.domain.repository.FeedRepository
import com.movee.movee.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindFeedRepository(
        repo : FeedRepositoryImpl
    ) : FeedRepository

    @Binds
    abstract fun bindGetPopularMoviesCase(
        useCase : GetPopularMoviesUseCaseImpl
    ) : GetPopularMoviesUseCase

    @Binds
    abstract fun bindGetTrendingMoviesCase(
        useCase : GetTrendingMoviesUseCaseImpl
    ) : GetTrendingMoviesUseCase

    @Binds
    abstract fun bindUpcomingMoviesCase(
        useCase : GetUpcomingMoviesUseCaseImpl
    ) : GetUpcomingMoviesUseCase

    @Binds
    abstract fun bindTopRatedMoviesCase(
        useCase : GetTopRatedMoviesUseCaseImpl
    ) : GetTopRatedMoviesUseCase

}
