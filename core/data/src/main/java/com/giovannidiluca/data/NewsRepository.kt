package com.giovannidiluca.data

import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.NewsResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getArticles(): Flow<NewsResult<NewsResponse>>
}