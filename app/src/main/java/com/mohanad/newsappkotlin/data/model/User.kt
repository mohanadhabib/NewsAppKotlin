package com.mohanad.newsappkotlin.data.model

// User model that stored in shared preferences and firebase
data class User(
    val userId:String,
    val userEmail:String,
    val password:String
)
