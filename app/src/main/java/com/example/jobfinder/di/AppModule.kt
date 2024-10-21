package com.example.jobfinder.di

import com.example.jobfinder.BuildConfig
import com.example.jobfinder.data.remote.JobSearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                    .addHeader("X-RapidAPI-Host", "jsearch.p.rapidapi.com")

            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://jsearch.p.rapidapi.com/").client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    @Provides
    @Singleton
    fun provideJobSearchApi(retrofit: Retrofit): JobSearchApi {
        return retrofit.create(JobSearchApi::class.java)
    }
}