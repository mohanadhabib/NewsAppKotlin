package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.Country
import retrofit2.http.GET

// Countries api requests
interface CountriesApi {

    @GET("Country.json")
    suspend fun getAllCountries():List<Country>

}