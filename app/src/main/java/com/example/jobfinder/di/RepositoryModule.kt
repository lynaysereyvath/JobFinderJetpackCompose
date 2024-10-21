package com.example.jobfinder.di

import com.example.jobfinder.data.repository.JobSearchRepositoryImpl
import com.example.jobfinder.domain.repository.JobSearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindJobSearchRepository(jobSearchRepositoryImpl: JobSearchRepositoryImpl): JobSearchRepository
}