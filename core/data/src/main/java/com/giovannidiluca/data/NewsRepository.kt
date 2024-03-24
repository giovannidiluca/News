package com.giovannidiluca.data

import com.giovannidiluca.network.NewsPagingSource

interface NewsRepository {
    fun getArticles(): NewsPagingSource
}