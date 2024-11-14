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

    private val repository = BottomBookmarkRepository()

    var searchText by mutableStateOf("")

    fun deleteNews(news: News, context: Context){
        viewModelScope.launch {
            repository.deleteNews(news = news , context = context)
        }
    }

    fun getSavedNews(context: Context):LiveData<List<News>>{
        val list = MutableLiveData<List<News>>()
        viewModelScope.launch {
            list.value = repository.getSavedNews(context)
        }
        return list
    }

    fun getTimeElapsed(time:String):LiveData<String>{
        val data = MutableLiveData<String>()
        data.value = repository.getTimeElapsed(time)
        return data
    }

    fun getSearchedNews(searchTxt:String , list: List<News>):LiveData<List<News>>{
        val newList = MutableLiveData<List<News>>()
        newList.value = repository.getSearchedNews(searchTxt,list)
        return newList
    }
}