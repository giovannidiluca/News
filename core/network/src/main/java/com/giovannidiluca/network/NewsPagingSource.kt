package com.giovannidiluca.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giovannidiluca.network.model.ArticleResponse
import com.giovannidiluca.network.retrofit.NewsApi
import retrofit2.HttpException
import java.io.IOException


class NewsPagingSource(
    private val backend: NewsApi
) : PagingSource<Int, ArticleResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleResponse> {
        return try {
            val pageNumber = params.key ?: 1

            val response = backend.getHeadlines(pageNumber)
            val articles = response.articles

            LoadResult.Page(
                data = articles,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (articles.isEmpty()) null else pageNumber + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleResponse>): Int? = null
}