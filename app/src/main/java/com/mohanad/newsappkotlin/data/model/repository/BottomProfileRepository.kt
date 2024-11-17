package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mohanad.newsappkotlin.data.datasource.firebase.BottomProfileFirebase
import com.mohanad.newsappkotlin.data.datasource.room.SavedNewsDatabase
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.data.model.User
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BottomProfileRepository {
    // Firebase instance of profile view
    private val firebase = BottomProfileFirebase()
    // Get the current user info
    suspend fun getUserInfo(): User? {
        return firebase.getUserInfo()
    }
    // Get all news article available in the database
    fun getSavedNews(context:Context):LiveData<List<News>>{
        return SavedNewsDatabase.getDatabase(context).dao.getSavedNews()
    }
    // Calculate the time of the news article
    fun getTimeElapsed(time:String):String{
        var getTimeElapsed = ""
        try {
            val formatter = DateTimeFormatter.ISO_DATE_TIME

            val timeNow = LocalDateTime.now()

            val newsTime = OffsetDateTime.parse(time,formatter).toLocalTime()

            val duration = Duration.between(newsTime,timeNow)

            getTimeElapsed = duration.toHours().toString() + "h ago"

        }catch (e: DateTimeParseException){
            e.printStackTrace()
        }
        return getTimeElapsed
    }
    // Log out the current user
    fun logOut(){
        firebase.logOut()
    }
}