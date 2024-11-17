package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsSourceResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// News source retrofit instance
class NewsSourceRetrofit {
    companion object{
        // Api key of news sources api
        private const val API_KEY = "pub_5761060f9ff261324c5452fde96a0450adfb1"
        // Base url of news sources api
        private const val BASE_URL = "https://newsdata.io/api/1/"
        // Singleton retrofit instance
        private var retrofit:Retrofit? = null
        private fun getRetrofit():Retrofit?{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
    // Get news sources from api request
    suspend fun getNewsSource(onFailure:(Exception)->Unit): NewsSourceResponse?{
        return try {
            getRetrofit()
                ?.create(NewsSourceApi::class.java)
                ?.getNewsSource(API_KEY)
        }catch (e:Exception){
            onFailure(e)
            null
        }
    }
}