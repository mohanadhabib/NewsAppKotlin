package com.mohanad.newsappkotlin.data.datasource.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Countries retrofit instance
abstract class CountriesRetrofit {
    companion object {
        const val COUNTRIES_BASE_URL = "https://country-code-au6g.vercel.app/"
        private var retrofit:Retrofit? = null

        fun getRetrofit():Retrofit?{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(COUNTRIES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}