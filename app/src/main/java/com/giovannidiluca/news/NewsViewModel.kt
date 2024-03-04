package com.giovannidiluca.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovannidiluca.data.NewsRepositoryImpl
import com.giovannidiluca.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryImpl
) : ViewModel() {
    val articles = mutableListOf<Article>()

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getArticles()
        }
    }
}