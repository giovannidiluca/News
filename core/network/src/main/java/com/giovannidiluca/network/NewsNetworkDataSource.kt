package com.giovannidiluca.network

import com.giovannidiluca.network.model.NewsResponse

interface NewsNetworkDataSource {
    suspend fun getRemoteArticles(): NewsResponse
}