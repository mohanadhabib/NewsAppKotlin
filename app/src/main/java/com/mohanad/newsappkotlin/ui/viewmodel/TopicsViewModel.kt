package com.mohanad.newsappkotlin.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohanad.newsappkotlin.data.model.repository.TopicsRepository

class TopicsViewModel:ViewModel() {

    private val repository = TopicsRepository()

    var searchTxt by mutableStateOf("")

    val selectedTitles = mutableStateListOf<String>()

    // Get all available topics
    fun getAllTopics():LiveData<List<String>>{
        val list = MutableLiveData<List<String>>()
        list.value = repository.getAllTopics()
        return list
    }

    // Get only searched topics
    fun getSearchedTopics(name:String , list:List<String>):LiveData<List<String>>{
        val newList= MutableLiveData<List<String>>()
        newList.value = repository.getSearchedTopics(name,list)
        return newList
    }

    // Storing selected topics in firebase firestore
    fun storeTopics(list: List<String> , onSuccess:(Void?)->Unit , onFailure:(Exception)->Unit){
        repository.storeTopics(
            list = list,
            onSuccess = onSuccess,
            onFailure = onFailure)
    }

}