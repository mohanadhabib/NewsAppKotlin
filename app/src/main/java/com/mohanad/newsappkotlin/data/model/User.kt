package com.mohanad.newsappkotlin.data.model

// User model that stored in shared preferences and firebase
data class User(

    val userId:String? = "",

    val userName:String? = "",

    val userFullName:String? = "",

    val phoneNumber:String? = "",

    val userEmail:String? = "",

    val password:String? = "",

    val userImg:String? = "",

    val country:String? = "",

    val listOfTopics:List<String>? = emptyList(),

    val newsSource:List<String>? = emptyList()
)
