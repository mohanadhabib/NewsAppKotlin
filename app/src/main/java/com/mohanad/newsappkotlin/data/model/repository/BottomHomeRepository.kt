package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsRetrofit
import com.mohanad.newsappkotlin.data.datasource.room.SavedNewsDatabase
import com.mohanad.newsappkotlin.data.model.News
import com.mohanad.newsappkotlin.data.model.NewsResponse
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BottomHomeRepository {
    // Retrofit instance of home view
    private val retrofit = NewsRetrofit()
    // Get latest news of general category
    suspend fun getLatestNews(): NewsResponse?{
        return retrofit.getLatestNews()
    }
    // Get news by category
    suspend fun getNewsByCategory(category:String): NewsResponse?{
        return retrofit.getNewsByCategory(category)
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

        }catch (e:DateTimeParseException){
            e.printStackTrace()
        }
        return getTimeElapsed
    }
    // Insert a single news article into the database
    suspend fun insertNews(news:News , context: Context){
        SavedNewsDatabase.getDatabase(context).dao.insertNews(news = news)
    }
    // Get all news article available in the database
    fun getSavedNews(context: Context):LiveData<List<News>>{
        return SavedNewsDatabase.getDatabase(context).dao.getSavedNews()
    }
    // Get searched news from the full list of news
    fun getSearchedNews(searchTxt:String,list:List<News>):List<News>{
        val newList = mutableListOf<News>()
        list.forEach { item ->
            if(item.title.contains(searchTxt,ignoreCase = true)){
                newList.add(item)
            }
        }
        return newList
    }
    // Control if this article is already saved not to save it again
    fun isContainNews(news: News , list: List<News>):Boolean{
        var isContainThisNews = false
        list.forEach { item ->
            if(news.title == item.title){
                isContainThisNews = true
            }
        }
        return isContainThisNews
    }
}