package com.ian.app.muviepedia.di.module

import com.ian.app.muviepedia.core.data.dataSource.remote.api.MovieApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.api.MoviePaginationApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.api.TelevisionApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.api.TelevisionPaginationApiInterface
import com.ian.app.muviepedia.di.qualifier.MovieApiInterfaceQualifier
import com.ian.app.muviepedia.di.qualifier.MoviePagingApiInterfaceQualifier
import com.ian.app.muviepedia.di.qualifier.TelevisionApiInterfaceQualifier
import com.ian.app.muviepedia.di.qualifier.TelevisionPagingApiInterfaceQualifier
import com.ian.app.muviepedia.di.scope.ApplicationScoped
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)

        return builder.build()
    }

    @Provides
    @ApplicationScoped
    @TelevisionApiInterfaceQualifier
    fun provideTelevisionApiInterface(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): TelevisionApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(TelevisionApiInterface::class.java)
    }

    @Provides
    @ApplicationScoped
    @TelevisionPagingApiInterfaceQualifier
    fun provideTelevisionPagingApiInterface(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): TelevisionPaginationApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(TelevisionPaginationApiInterface::class.java)
    }

    @Provides
    @ApplicationScoped
    @MovieApiInterfaceQualifier
    fun provideMovieApiInterface(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): MovieApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApiInterface::class.java)
    }

    @Provides
    @ApplicationScoped
    @MoviePagingApiInterfaceQualifier
    fun provideMoviePagingApiInterface(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): MoviePaginationApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(MoviePaginationApiInterface::class.java)
    }
}
