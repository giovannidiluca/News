package com.giovannidiluca.network.retrofit

import com.giovannidiluca.network.NewsNetworkDataSource
import com.giovannidiluca.network.model.NewsResponse
import com.giovannidiluca.network.retrofit.RetrofitNewsNetwork.Companion.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface NewsApi {
    @GET("everything?q=bitcoin&apiKey=$API_KEY")
    suspend fun getHeadlines(): NewsResponse
}

@Singleton
class RetrofitNewsNetwork @Inject constructor() : NewsNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    override suspend fun getRemoteArticles(): NewsResponse = networkApi.getHeadlines()

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "e59c6a753d8245f6bd3a80fdc9aa37ec"
    }
}