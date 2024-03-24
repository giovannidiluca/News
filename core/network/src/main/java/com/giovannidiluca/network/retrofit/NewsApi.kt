package com.giovannidiluca.network.retrofit

import com.giovannidiluca.network.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

const val API_KEY = "29c8cb10ad8e4397a2732a30d24b8e51"
const val PAGE_SIZE = 10

interface NewsApi {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("country") country: String = Locale.getDefault().country,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}