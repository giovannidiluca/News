package com.giovannidiluca.data

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.NewsResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsNetworkDataSource: NewsNetworkDataSource) :
    NewsRepository {

    override suspend fun getArticles(): Flow<NewsResult<NewsResponse>> =
        newsNetworkDataSource.getRemoteArticles()
}