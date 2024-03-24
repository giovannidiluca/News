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

    companion object {
        const val PAGE_SIZE = 10
    }

    fun fetchArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { newsRepository.getArticles() }
        )
            .flow
            .map { pagingData ->
                pagingData
                    .map { articleResponse -> articleResponse.toArticle() }
            }
            .cachedIn(viewModelScope)
    }
}