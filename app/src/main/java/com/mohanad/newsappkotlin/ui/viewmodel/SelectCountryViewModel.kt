package com.mohanad.newsappkotlin.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.Country
import com.mohanad.newsappkotlin.data.model.repository.SelectCountryRepository
import kotlinx.coroutines.launch

class SelectCountryViewModel :ViewModel() {

    private val repository = SelectCountryRepository()

    var searchTxt by mutableStateOf("")

    var selectedName by mutableStateOf("")

    // Get Countries names and flags from the api
    fun getAllCountries(onFailure: (Exception) -> Unit):LiveData<List<Country>>{
        val list = MutableLiveData<List<Country>>()
        viewModelScope.launch {
            list.value = repository.getAllCountries(onFailure = onFailure)
        }
        return list
    }

    // Filtering the countries list to get the matched searched countries list
    fun getSearchedCountries(name:String,list: List<Country>):LiveData<List<Country>>{
        val newList = MutableLiveData<List<Country>>()
        newList.value = repository.getSearchedCountries(
            list = list,
            name = name)
        return newList
    }

    // Storing the country name into the user info in firestore
    fun storeUserCountry(countryName:String,onSuccess:(Void?) -> Unit,onFailure:(Exception)->Unit){
        repository.storeUserCountry(
            countryName = countryName,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}