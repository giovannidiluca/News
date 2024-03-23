package com.giovannidiluca.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovannidiluca.data.NewsRepositoryImpl
import com.giovannidiluca.data.model.Article
import com.giovannidiluca.data.toArticle
import com.giovannidiluca.network.retrofit.ApiException
import com.giovannidiluca.network.retrofit.ApiFailure
import com.giovannidiluca.network.retrofit.ApiSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    var uiState: StateFlow<NewsUiState> = _uiState

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch {
            newsRepository.getArticles()
                .catch { _uiState.value = NewsUiState.Error }
                .collect { result ->
                    _uiState.value = when (result) {
                        is ApiException, is ApiFailure -> NewsUiState.Error
                        is ApiSuccess -> NewsUiState.Success(result.data.articles.map { it.toArticle() })
                    }
                }
        }
    }

    sealed class NewsUiState {
        object Error : NewsUiState()
        object Loading : NewsUiState()
        data class Success(val articles: List<Article>) : NewsUiState()
    }
}