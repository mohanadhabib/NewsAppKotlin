package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Countries retrofit instance
class CountriesRetrofit {
    companion object {
        // Base url of countries api
        const val COUNTRIES_BASE_URL = "https://country-code-au6g.vercel.app/"
        // Singleton retrofit instance
        private var retrofit:Retrofit? = null
        private fun getRetrofit():Retrofit?{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(COUNTRIES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
    // Get all countries from the countries api request
    suspend fun getAllCountries(onFailure: (Exception) -> Unit):List<Country>?{
        return try {
            getRetrofit()
                ?.create(CountriesApi::class.java)
                ?.getAllCountries()
        }
        catch (e:Exception){
            onFailure(e)
            null
        }
    }
}