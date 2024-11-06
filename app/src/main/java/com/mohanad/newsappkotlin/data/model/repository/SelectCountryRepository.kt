package com.mohanad.newsappkotlin.data.model.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.data.datasource.retrofit.CountriesApi
import com.mohanad.newsappkotlin.data.datasource.retrofit.CountriesRetrofit
import com.mohanad.newsappkotlin.data.model.Country
import com.mohanad.newsappkotlin.util.FirebaseConstants

class SelectCountryRepository {

    // Get Countries names and flags from the api
    suspend fun getAllCountries(onFailure: (Exception) -> Unit):List<Country>?{
        return try {
            CountriesRetrofit.getRetrofit()
                ?.create(CountriesApi::class.java)
                ?.getAllCountries()
        }
        catch (e:Exception){
            onFailure(e)
            null
        }
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
        try {
            Firebase.firestore.collection(FirebaseConstants.USER_COLLECTION)
                .document(Firebase.auth.uid!!)
                .update(
                    mapOf(pair = "country" to countryName)
                ).addOnSuccessListener {
                    onSuccess(it)
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        }catch (e:Exception){
            onFailure(e)
        }
    }
}