package com.giovannidiluca.data.di

import com.giovannidiluca.data.NewsRepository
import com.giovannidiluca.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsNewsRepository(
        newsRepository: NewsRepositoryImpl
    ): NewsRepository
}
