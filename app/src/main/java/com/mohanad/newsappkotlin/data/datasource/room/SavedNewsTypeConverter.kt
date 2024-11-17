package com.mohanad.newsappkotlin.data.datasource.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohanad.newsappkotlin.data.model.ArticleSource

// Article Source type converter for saved news room db
class SavedNewsTypeConverter {
    // Deserialize the json into article source object
    @TypeConverter
    fun toArticleSource(sourceString: String?): ArticleSource? {
        val type = object : TypeToken<ArticleSource>() {}.type
        return Gson().fromJson(sourceString, type)
    }
    // Serialize the article source object into json
    @TypeConverter
    fun fromArticleSource(source: ArticleSource?): String? {
        return Gson().toJson(source)
    }
}