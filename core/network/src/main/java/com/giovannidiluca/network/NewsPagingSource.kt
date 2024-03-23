package com.giovannidiluca.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giovannidiluca.network.model.ArticleResponse
import com.giovannidiluca.network.retrofit.NewsApi
import retrofit2.HttpException
import java.io.IOException


class NewsPagingSource(
    private val backend: NewsApi
) : PagingSource<String, ArticleResponse>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ArticleResponse> {
        return try {
            val response = backend.getHeadlines()

            with(response.articles) {
                LoadResult.Page(
                    data = this,
                    prevKey = null,
                    nextKey = this.last().url
                )
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, ArticleResponse>): String? {
        val anchorPosition = state.anchorPosition ?: return null
        val response = state.closestItemToPosition(anchorPosition) ?: return null
        return response.url
    }
}