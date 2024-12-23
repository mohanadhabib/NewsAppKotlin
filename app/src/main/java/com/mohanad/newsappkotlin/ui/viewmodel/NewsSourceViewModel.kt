package com.mohanad.newsappkotlin.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.NewsSource
import com.mohanad.newsappkotlin.data.model.NewsSourceResponse
import com.mohanad.newsappkotlin.data.model.repository.NewsSourceRepository
import kotlinx.coroutines.launch

class NewsSourceViewModel :ViewModel() {
    // Repository instance
    private val repository = NewsSourceRepository()
    // State variables
    var searchTxt by mutableStateOf("")
    val selectedList = mutableStateListOf<String>()
    // Get all news sources from the restApi
    fun getNewsSource(onFailure:(Exception)->Unit):LiveData<NewsSourceResponse>{
        val response = MutableLiveData<NewsSourceResponse>()
        viewModelScope.launch {
            response.value = repository.getNewsSource(
                onFailure = onFailure
            )
        }
        return response
    }
    // Get only the searched news source from the complete list
    fun getSearchedNewsSource(title:String , allNewsList:List<NewsSource>):LiveData<List<NewsSource>>{
        val newList = MutableLiveData<List<NewsSource>>()
        newList.value = repository.getSearchedNewsSource(
            title = title,
            allNewsList = allNewsList
        )
        return newList
    }
    // Store user's preferred news sources into firebase firestore
    fun storeNewsSources(list: List<String> , onSuccess:(Void?) -> Unit , onFailure: (Exception) -> Unit ){
        repository.storeNewsSources(
            list = list,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}