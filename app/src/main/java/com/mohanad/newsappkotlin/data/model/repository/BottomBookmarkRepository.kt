package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mohanad.newsappkotlin.data.datasource.room.SavedNewsDatabase
import com.mohanad.newsappkotlin.data.model.News
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BottomBookmarkRepository {
    // Delete news from saved news
    suspend fun deleteNews(news: News, context: Context){
        SavedNewsDatabase.getDatabase(context).dao.deleteNews(news = news)
    }
    // Get all saved news from saved news db
    fun getSavedNews(context: Context):LiveData<List<News>>{
        return SavedNewsDatabase.getDatabase(context).dao.getSavedNews()
    }
    // Calculate the time of each time article
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
    // Get a searched list from the full saved news list
    fun getSearchedNews(searchTxt:String , list: List<News>): List<News>{
        val newList = mutableListOf<News>()
        list.forEach { item ->
            if(item.title.contains(searchTxt,true)){
                newList.add(item)
            }
        }
        return newList
    }
}