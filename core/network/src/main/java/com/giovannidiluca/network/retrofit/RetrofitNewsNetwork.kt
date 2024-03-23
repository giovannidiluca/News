package com.giovannidiluca.network.retrofit

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.RetrofitNewsNetwork.Companion.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

private interface NewsApi {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = Locale.getDefault().country,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}


@Singleton
class RetrofitNewsNetwork @Inject constructor() : NewsNetworkDataSource {
    private val networkApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)

    override suspend fun getRemoteArticles(): Flow<NewsResult<NewsResponse>> =
        flow { emit(handleApi { networkApi.getHeadlines() }) }.flowOn(Dispatchers.IO)

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = ""
    }
}