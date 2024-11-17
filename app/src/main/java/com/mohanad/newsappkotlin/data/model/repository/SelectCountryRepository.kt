package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.firebase.SelectCountryFirebase
import com.mohanad.newsappkotlin.data.datasource.retrofit.CountriesRetrofit
import com.mohanad.newsappkotlin.data.model.Country

class SelectCountryRepository {
    // Data source instances
    private val firebase = SelectCountryFirebase()
    private val retrofit = CountriesRetrofit()
    // Get Countries names and flags from the api
    suspend fun getAllCountries(onFailure: (Exception) -> Unit):List<Country>?{
        return retrofit.getAllCountries(
            onFailure = onFailure
        )
    }
    // Filtering the countries list to get the matched searched countries list
    fun getSearchedCountries(name:String , list:List<Country>):List<Country>{
        val newList:MutableList<Country> = mutableListOf()
        list.forEach {
            if(it.name.contains(name,true)){
                newList.add(it)
            }
        }
        return newList
    }
    // Storing the country name into the user info in firestore
    fun storeUserCountry(countryName:String,onSuccess:(Void?) -> Unit,onFailure:(Exception)->Unit){
        firebase.storeUserCountry(
            countryName = countryName,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}