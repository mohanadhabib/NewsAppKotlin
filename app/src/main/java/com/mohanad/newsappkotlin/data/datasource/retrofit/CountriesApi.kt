package com.mohanad.newsappkotlin.data.datasource.retrofit

import com.mohanad.newsappkotlin.data.model.Country
import retrofit2.http.GET

// Countries api requests
interface CountriesApi {
    // Get all available countries from the api
    @GET("Country.json")
    suspend fun getAllCountries():List<Country>
}