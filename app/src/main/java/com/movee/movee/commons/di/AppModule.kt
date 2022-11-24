package com.movee.movee.commons.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.movee.movee.BuildConfig
import com.movee.movee.commons.constants.ApiConstants
import com.movee.movee.data.api.MoviesApi
import com.movee.movee.data.api.response.NetworkResponseCallAdapterFactory
import com.movee.movee.data.repository.FeedRepositoryImpl
import com.movee.movee.domain.repository.FeedRepository
import com.movee.movee.domain.usecase.GetPopularMoviesUseCase
import com.movee.movee.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context : Context) : RequestManager =
        Glide.with(context)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpBuilder: OkHttpClient.Builder) : Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseCallAdapterFactory)
            .client(
                okHttpBuilder.addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                    val url = chain.request().url
                    val newUrl = url.newBuilder().addQueryParameter(
                        "api_key",
                        BuildConfig.TOKEN
                    ).build()
                    request.url(newUrl)
                    chain.proceed(request.build())
                }.build()
            )
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : MoviesApi =
        retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient.Builder =
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

}