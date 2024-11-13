package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsRetrofit
import com.mohanad.newsappkotlin.data.model.NewsResponse
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class BottomHomeRepository {

    private val retrofit = NewsRetrofit()

    suspend fun getLatestNews(): NewsResponse?{
        return retrofit.getLatestNews()
    }

    suspend fun getNewsByCategory(category:String): NewsResponse?{
        return retrofit.getNewsByCategory(category)
    }

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
}