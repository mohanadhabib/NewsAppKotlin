package com.mohanad.newsappkotlin.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.NewsResponse
import com.mohanad.newsappkotlin.data.model.repository.BottomHomeRepository
import kotlinx.coroutines.launch

class BottomHomeViewModel : ViewModel() {

    private val repository = BottomHomeRepository()

    var searchTxt by mutableStateOf("")
    var selectedIndex by mutableIntStateOf(0)
    var category by mutableStateOf("General")


    fun getLatestNews(): LiveData<NewsResponse>{
        val data = MutableLiveData<NewsResponse>()
        viewModelScope.launch {
            data.value = repository.getLatestNews()
        }
        return data
    }

    fun getNewsByCategory(category: String):LiveData<NewsResponse>{
        val data = MutableLiveData<NewsResponse>()
        viewModelScope.launch {
            data.value = repository.getNewsByCategory(category)
        }
        return data
    }

    fun getTimeElapsed(time:String):LiveData<String>{
        val timeElapsed = MutableLiveData<String>()
        timeElapsed.value = repository.getTimeElapsed(time)
        return timeElapsed
    }
}