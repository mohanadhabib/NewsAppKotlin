package com.mohanad.newsappkotlin.data.datasource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mohanad.newsappkotlin.data.model.News
// Data access object of saved news room db
@Dao
interface SavedNewsDao {
    // Insert a single news article into the database
    @Insert
    suspend fun insertNews(news:News)
    // Delete a single news article from the database
    @Delete
    suspend fun deleteNews(news: News)
    // Get all news article available in the database
    @Query("SELECT * FROM news")
    fun getSavedNews():LiveData<List<News>>
}