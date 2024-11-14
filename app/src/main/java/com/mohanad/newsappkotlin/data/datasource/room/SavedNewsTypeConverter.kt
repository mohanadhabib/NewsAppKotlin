package com.mohanad.newsappkotlin.data.datasource.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohanad.newsappkotlin.data.model.ArticleSource

class SavedNewsTypeConverter {

    @TypeConverter
    fun toArticleSource(sourceString: String?): ArticleSource? {
        val type = object : TypeToken<ArticleSource>() {}.type
        return Gson().fromJson(sourceString, type)
    }

    @TypeConverter
    fun fromArticleSource(source: ArticleSource?): String? {
        return Gson().toJson(source)
    }
}