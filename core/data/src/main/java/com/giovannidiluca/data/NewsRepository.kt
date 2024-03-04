package com.giovannidiluca.data

import com.giovannidiluca.data.model.Article

interface NewsRepository {
    suspend fun getArticles(): List<Article>
}