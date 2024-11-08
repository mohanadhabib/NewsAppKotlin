package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("article_id")
    val articleId:String?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("link")
    val link:String?,

    @SerializedName("keywords")
    val keywords:List<String>?,

    @SerializedName("creator")
    val creator:String?,

    @SerializedName("video_url")
    val videoUrl:String?,

    @SerializedName("description")
    val description:String?,

    @SerializedName("content")
    val content:String?,

    @SerializedName("pubDate")
    val pubDate:String?,

    @SerializedName("pubDateTZ")
    val pubDateTZ:String?,

    @SerializedName("image_url")
    val imageUrl:String?,

    @SerializedName("source_id")
    val sourceId:String?,

    @SerializedName("source_priority")
    val sourcePriority:Long?,

    @SerializedName("source_name")
    val sourceName:String?,

    @SerializedName("source_url")
    val sourceUrl:String?,

    @SerializedName("source_icon")
    val sourceIcon:String?,

    @SerializedName("language")
    val language:String?,

    @SerializedName("country")
    val country:List<String>?,

    @SerializedName("category")
    val category:List<String>?,

    @SerializedName("ai_tag")
    val aiTag:String?,

    @SerializedName("sentiment")
    val sentiment:String?,

    @SerializedName("sentiment_stats")
    val sentimentStats:String?,

    @SerializedName("ai_region")
    val aiRegion:String?,

    @SerializedName("ai_org")
    val aiOrg:String?,

    @SerializedName("duplicate")
    val duplicate:Boolean?

)
