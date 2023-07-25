package com.ian.app.muviepedia.di.module

import com.ian.app.muviepedia.di.scope.ApplicationScoped
import com.ian.app.muviepedia.core.data.dataSource.remote.api.ApiInterface
import com.ian.app.muviepedia.core.data.dataSource.remote.api.NetworkConstant
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {


    @Provides
    fun provideHttpClientForHomeScreen(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)

        return builder.build()
    }

    @Provides
    @ApplicationScoped
    fun provideApiInterfaceForHomeScreen(
        okHttpClient: OkHttpClient, moshi: Moshi
    ): ApiInterface {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(NetworkConstant.BASE_URL)
            .build()
            .create(ApiInterface::class.java)
    }

}