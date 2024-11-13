package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofit {

    companion object{

        private const val BASE_URL = "https://newsapi.org/v2/"
        private const val API_KEY = "4f5c7ab474bc4dd9b26ffc259bd23e31"

        private var retrofit:Retrofit? = null

        fun getRetrofit():Retrofit?{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

    suspend fun getLatestNews():NewsResponse?{
        return getRetrofit()
                ?.create(NewsApi::class.java)
                ?.getLatestNews(
                    category = "general",
                    apiKey = API_KEY
                )
    }

    suspend fun getNewsByCategory(category:String):NewsResponse?{
        return getRetrofit()
            ?.create(NewsApi::class.java)
            ?.getLatestNews(
                category = category,
                apiKey = API_KEY
            )
    }
}