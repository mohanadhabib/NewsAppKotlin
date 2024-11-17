package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.firebase.TopicsFirebase

class TopicsRepository {
    // Firebase instance of topics select view
    private val firebase = TopicsFirebase()
    // Get all available topics
    fun getAllTopics():List<String>{
        val topicsList = listOf("Top" , "National" , "Sports" ,"Lifestyle" , "Business" , "Health" , "Fashion" , "Technology" , "Science" , "Arts" , "Politics")
        return topicsList
    }
    // Get only searched topics
    fun getSearchedTopics(name:String , list:List<String>):List<String>{
        val newList = mutableListOf<String>()
        list.forEach {
            if(it.contains(name,ignoreCase = true)){
                newList.add(it)
            }
        }
        return newList
    }
    // Storing selected topics in firebase firestore
    fun storeTopics(list: List<String> , onSuccess:(Void?)->Unit , onFailure:(Exception)->Unit){
        firebase.storeTopics(
            list = list,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}