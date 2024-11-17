package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.firebase.BottomExploreFirebase

class BottomExploreRepository {
    // Firebase instance of explore view
    private val firebase = BottomExploreFirebase()
    // Get unselected topics from the startup choose topics screen
    fun getSuggestedTopics(onFailure:(Exception)->Unit) :List<String> {
        return firebase.getSuggestedTopics { onFailure(it) }
    }
}