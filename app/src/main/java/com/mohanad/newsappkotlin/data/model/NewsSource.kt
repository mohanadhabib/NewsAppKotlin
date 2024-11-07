package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

// News source model parsed from news api
data class NewsSource(
    @SerializedName("id")
    val id:String,

    @SerializedName("name")
    val name:String,

    @SerializedName("url")
    val url:String,

    @SerializedName("icon")
    val icon:String,

    @SerializedName("priority")
    val priority:Int,

    @SerializedName("description")
    val description:String,

    @SerializedName("category")
    val category:List<String>,

    @SerializedName("language")
    val language:List<String>,

    @SerializedName("country")
    val country:List<String>,

    @SerializedName("last_fetch")
    val lastFetch:String
)