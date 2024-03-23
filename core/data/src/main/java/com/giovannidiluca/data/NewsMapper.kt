package com.giovannidiluca.data

import com.giovannidiluca.data.model.Article
import com.giovannidiluca.network.model.ArticleResponse

fun ArticleResponse.toArticle() = Article(
    title = title,
    description = description ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = DateFormatterUtils.transformDate(publishedAt),
)