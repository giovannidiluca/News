package com.giovannidiluca.data.model


data class Article(
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val author: String,
    val active: Boolean
)