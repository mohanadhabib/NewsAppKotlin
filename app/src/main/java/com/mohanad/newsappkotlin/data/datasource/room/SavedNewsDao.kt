package com.mohanad.newsappkotlin.data.datasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mohanad.newsappkotlin.data.model.News

@Dao
interface SavedNewsDao {

    @Insert
    suspend fun insertNews(news:News)

    @Delete
    suspend fun deleteNews(news: News)

    @Query("SELECT * FROM news")
    suspend fun getSavedNews():List<News>
}