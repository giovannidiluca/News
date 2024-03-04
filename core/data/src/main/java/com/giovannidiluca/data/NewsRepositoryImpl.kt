package com.giovannidiluca.data

import com.giovannidiluca.data.model.Article
import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.model.ArticleResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsNetworkDataSource: NewsNetworkDataSource) :
    NewsRepository {

    override suspend fun getArticles() =
        newsNetworkDataSource.getRemoteArticles().articles.map {
            it.toArticle()
        }

    private fun ArticleResponse.toArticle() = Article(
        title = title,
        description = description ?: "",
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
    )
}