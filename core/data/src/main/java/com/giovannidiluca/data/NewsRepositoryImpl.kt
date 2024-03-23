package com.giovannidiluca.data

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.NewsPagingSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsNetworkDataSource: NewsNetworkDataSource) :
    NewsRepository {

    override fun getArticles(): NewsPagingSource =
        newsNetworkDataSource.getRemoteArticles()
}