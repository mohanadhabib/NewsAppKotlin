package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// News retrofit instance
class NewsRetrofit {
    companion object{
        // Base url of news api
        private const val BASE_URL = "https://newsapi.org/v2/"
        // Api key of news api
        private const val API_KEY = "4f5c7ab474bc4dd9b26ffc259bd23e31"
        // Singleton retrofit instance
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
    // Get latest news of general category
    suspend fun getLatestNews():NewsResponse?{
        return getRetrofit()
                ?.create(NewsApi::class.java)
                ?.getLatestNews(
                    category = "general",
                    apiKey = API_KEY
                )
    }
    // Get news by category
    suspend fun getNewsByCategory(category:String):NewsResponse?{
        return getRetrofit()
            ?.create(NewsApi::class.java)
            ?.getLatestNews(
                category = category.lowercase(),
                apiKey = API_KEY
            )
    }
}