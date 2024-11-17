package com.mohanad.newsappkotlin.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.data.model.repository.BottomBookmarkRepository
import kotlinx.coroutines.launch

class BottomBookmarkViewModel : ViewModel() {
    // Repository instance
    private val repository = BottomBookmarkRepository()
    // State variables
    var searchText by mutableStateOf("")
    // Delete news from saved news
    fun deleteNews(news: News, context: Context){
        viewModelScope.launch {
            repository.deleteNews(news = news, context = context)
        }
    }
    // Get all saved news from saved news db
    fun getSavedNews(context: Context):LiveData<List<News>>{
        return repository.getSavedNews(context)
    }
    // Calculate the time of each time article
    fun getTimeElapsed(time:String):LiveData<String>{
        val data = MutableLiveData<String>()
        data.value = repository.getTimeElapsed(time)
        return data
    }
    // Get a searched list from the full saved news list
    fun getSearchedNews(searchTxt:String , list: List<News>):LiveData<List<News>>{
        val newList = MutableLiveData<List<News>>()
        newList.value = repository.getSearchedNews(searchTxt,list)
        return newList
    }
}