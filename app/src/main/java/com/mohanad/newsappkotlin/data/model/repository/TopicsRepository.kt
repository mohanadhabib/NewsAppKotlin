package com.mohanad.newsappkotlin.data.model.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.util.FirebaseConstants

class TopicsRepository {

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
        val id = Firebase.auth.uid
        try {
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(id!!)
                .update(mapOf("listOfTopics" to list))
                .addOnSuccessListener {onSuccess(it)}
                .addOnFailureListener {onFailure(it)}
        }
        catch (e:Exception){
            onFailure(e)
        }
    }
}