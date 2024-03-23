package com.giovannidiluca.network.retrofit

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.NewsPagingSource
import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.RetrofitNewsNetwork.Companion.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

interface NewsApi {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = Locale.getDefault().country,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}


@Singleton
class RetrofitNewsNetwork @Inject constructor() : NewsNetworkDataSource {
    private val networkApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)

    override fun getRemoteArticles(): NewsPagingSource = NewsPagingSource(backend = networkApi)

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "29c8cb10ad8e4397a2732a30d24b8e51"
    }
}