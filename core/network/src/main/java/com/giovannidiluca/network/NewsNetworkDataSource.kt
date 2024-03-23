package com.giovannidiluca.network

import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.NewsResult
import kotlinx.coroutines.flow.Flow

interface NewsNetworkDataSource {
    suspend fun getRemoteArticles(): Flow<NewsResult<NewsResponse>>
}