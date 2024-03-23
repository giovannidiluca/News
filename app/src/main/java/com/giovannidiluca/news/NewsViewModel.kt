package com.giovannidiluca.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.giovannidiluca.data.NewsRepositoryImpl
import com.giovannidiluca.data.model.Article
import com.giovannidiluca.data.toArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryImpl
) : ViewModel() {

    init {
        fetchArticles()
    }

    fun fetchArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { newsRepository.getArticles() }
        )
            .flow
            .map { pagingData ->
                pagingData
                    .map { it.toArticle() }
                    .map { it }
            }
            .cachedIn(viewModelScope)
    }
}