package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import com.mohanad.newsappkotlin.data.datasource.room.SavedNewsDatabase
import com.mohanad.newsappkotlin.data.model.News
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BottomBookmarkRepository {

    suspend fun deleteNews(news: News, context: Context){
        SavedNewsDatabase.getDatabase(context).dao.deleteNews(news = news)
    }

    suspend fun getSavedNews(context: Context):List<News>{
        return SavedNewsDatabase.getDatabase(context).dao.getSavedNews()
    }

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