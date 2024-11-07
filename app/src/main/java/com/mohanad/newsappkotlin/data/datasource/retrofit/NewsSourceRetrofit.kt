package com.mohanad.newsappkotlin.data.datasource.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// News source retrofit instance
abstract class NewsSourceRetrofit {

    companion object{

        const val API_KEY = "pub_5761060f9ff261324c5452fde96a0450adfb1"

        private const val BASE_URL = "https://newsdata.io/api/1/"

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
}