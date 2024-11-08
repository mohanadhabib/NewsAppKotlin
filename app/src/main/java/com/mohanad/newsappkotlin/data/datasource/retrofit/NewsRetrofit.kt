package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.NewsResponse
import com.mohanad.newsappkotlin.data.model.NewsSourceResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// News source retrofit instance
class NewsRetrofit {

    companion object{

        private const val API_KEY = "pub_5761060f9ff261324c5452fde96a0450adfb1"

        private const val BASE_URL = "https://newsdata.io/api/1/"

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

    suspend fun getNewsSource(onFailure:(Exception)->Unit): NewsSourceResponse?{
        return try {
            getRetrofit()
                ?.create(NewsApi::class.java)
                ?.getNewsSource(API_KEY)
        }catch (e:Exception){
            onFailure(e)
            null
        }
    }

    suspend fun getLatestNews(onFailure: (Exception) -> Unit):NewsResponse?{
        return try{
            getRetrofit()
                ?.create(NewsApi::class.java)
                ?.getLatestNews(API_KEY)
        }catch (e:Exception){
            onFailure(e)
            null
        }
    }
}