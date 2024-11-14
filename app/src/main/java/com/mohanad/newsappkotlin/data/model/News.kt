package com.mohanad.newsappkotlin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class News(

    @PrimaryKey(true)
    @ColumnInfo("id")
    @Transient
    var id:Int,

    @SerializedName("source")
    var source:ArticleSource?,

    @SerializedName("author")
    var author:String?,

    @ColumnInfo("title")
    @SerializedName("title")
    var title:String,

    @SerializedName("description")
    var description:String?,

    @ColumnInfo("url")
    @SerializedName("url")
    var url:String,

    @ColumnInfo("image")
    @SerializedName("urlToImage")
    var urlToImage:String,

    @ColumnInfo("publishedAt")
    @SerializedName("publishedAt")
    var publishedAt:String,

    @SerializedName("content")
    var content:String?,

    @ColumnInfo("category")
    var category:String?
)
