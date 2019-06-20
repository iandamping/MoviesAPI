package com.ian.app.muviepedia.di

import com.google.gson.GsonBuilder
import com.ian.app.muviepedia.api.ApiInterface
import com.ian.app.muviepedia.util.MovieConstant.baseUrl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
val networkMod = module {
    single { createOkHttpClient() }
    single { createClient<ApiInterface>(get()) }
}


private fun createOkHttpClient(): OkHttpClient {

    val timeOut = 60L
    val dispatcher = Dispatcher().apply {
        maxRequests = 20
        maxRequestsPerHost = 20
    }

    val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val ongoing = chain.request().newBuilder()
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header1), ctx.resources.getString(R.string.fcm_key))
//                ongoing.addHeader(ctx.resources.getString(R.string.retrofit_header2), ctx.resources.getString(R.string.retrofit_value_header2))
                chain.proceed(ongoing.build())
            }
    return okHttpBuilder.build()
}

private inline fun <reified T> createClient(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(baseUrl)
            .build()
    return retrofit.create(T::class.java)
}
