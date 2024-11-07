package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsSourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

// News Source api request
interface NewsSourceApi {

    @GET("sources")
    suspend fun getNewsSource(@Query("apikey") apiKey:String):NewsSourceResponse

}