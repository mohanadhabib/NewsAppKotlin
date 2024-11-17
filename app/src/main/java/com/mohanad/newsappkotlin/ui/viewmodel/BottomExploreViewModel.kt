package com.mohanad.newsappkotlin.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mohanad.newsappkotlin.data.model.repository.BottomExploreRepository

class BottomExploreViewModel : ViewModel() {
    // Repository instance
    private val repository  = BottomExploreRepository()
    // Get unselected topics from the startup choose topics screen
    fun getSuggestedTopics(onFailure:(Exception)->Unit) :List<String> {
        return repository.getSuggestedTopics { onFailure(it) }
    }
}