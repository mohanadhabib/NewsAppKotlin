package com.mohanad.newsappkotlin.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.data.model.NewsResponse
import com.mohanad.newsappkotlin.data.model.repository.BottomHomeRepository
import kotlinx.coroutines.launch

class BottomHomeViewModel : ViewModel() {
    // Repository instance
    private val repository = BottomHomeRepository()
    // State variables
    var searchTxt by mutableStateOf("")
    var selectedIndex by mutableIntStateOf(0)
    var category by mutableStateOf("General")
    // Get latest news of general category
    fun getLatestNews(): LiveData<NewsResponse>{
        val data = MutableLiveData<NewsResponse>()
        viewModelScope.launch {
            data.value = repository.getLatestNews()
        }
        return data
    }
    // Get news by category
    fun getNewsByCategory(category: String):LiveData<NewsResponse>{
        val data = MutableLiveData<NewsResponse>()
        viewModelScope.launch {
            data.value = repository.getNewsByCategory(category)
        }
        return data
    }
    // Calculate the time of each time article
    fun getTimeElapsed(time:String):LiveData<String>{
        val timeElapsed = MutableLiveData<String>()
        timeElapsed.value = repository.getTimeElapsed(time)
        return timeElapsed
    }
    // Insert a single news article into the database
    fun insertNews(news: News, context: Context){
        viewModelScope.launch {
            repository.insertNews(news, context)
        }
    }
    // Get all news article available in the database
    fun getSavedNews(context: Context):LiveData<List<News>>{
        return repository.getSavedNews(context)
    }
    // Get searched news from the full list of news
    fun getSearchedNews(searchTxt:String,list:List<News>):LiveData<List<News>>{
        val liveData = MutableLiveData<List<News>>()
        liveData.value = repository.getSearchedNews(searchTxt,list)
        return liveData
    }
    // Control if this article is already saved not to save it again
    fun isContainNews(news: News , list: List<News>):Boolean{
        return repository.isContainNews(news,list)
    }
}