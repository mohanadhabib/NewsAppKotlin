package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey:String
    ):NewsResponse

}