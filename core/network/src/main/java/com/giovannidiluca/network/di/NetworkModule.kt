package com.giovannidiluca.network.di

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.retrofit.RetrofitNewsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Binds
    fun binds(impl: RetrofitNewsNetwork): NewsNetworkDataSource
}
