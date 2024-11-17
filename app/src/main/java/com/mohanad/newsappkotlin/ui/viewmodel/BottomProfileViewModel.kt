package com.mohanad.newsappkotlin.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.data.model.repository.BottomProfileRepository
import kotlinx.coroutines.launch

class BottomProfileViewModel : ViewModel() {
    // Repository instance
    private val repository = BottomProfileRepository()
    // Get the current user info
    fun getUserInfo():LiveData<User>{
        val user = MutableLiveData<User>()
        viewModelScope.launch {
            user.value = repository.getUserInfo()
        }
        return user
    }
    // Get all news article available in the database
    fun getSavedNews(context: Context):LiveData<List<News>>{
        return repository.getSavedNews(context)
    }
    // Calculate the time of the news article
    fun getTimeElapsed(time:String):LiveData<String>{
        val timeElapsed = MutableLiveData<String>()
        timeElapsed.value = repository.getTimeElapsed(time)
        return timeElapsed
    }
    // Log out the current user
    fun logOut(){
        repository.logOut()
    }
}