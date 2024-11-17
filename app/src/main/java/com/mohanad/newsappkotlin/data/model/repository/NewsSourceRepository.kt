package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.firebase.NewsSourceFirebase
import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsSourceRetrofit
import com.mohanad.newsappkotlin.data.model.NewsSource
import com.mohanad.newsappkotlin.data.model.NewsSourceResponse

class NewsSourceRepository {
    // Data source instances
    private val firebase = NewsSourceFirebase()
    private val retrofit = NewsSourceRetrofit()
    // Get all news sources from the restApi
    suspend fun getNewsSource(onFailure:(Exception)->Unit): NewsSourceResponse?{
        return retrofit.getNewsSource (
            onFailure = onFailure
        )
    }
    // Get only the searched news source from the complete list
    fun getSearchedNewsSource(title:String ,allNewsList:List<NewsSource> ):List<NewsSource>{
        val list = mutableListOf<NewsSource>()
        allNewsList.forEach {
            if(it.name.contains(title)){
                list.add(it)
            }
        }
        return list
    }
    // Store user's preferred news sources into firebase firestore
    fun storeNewsSources(list: List<String> , onSuccess:(Void?) -> Unit , onFailure: (Exception) -> Unit ){
        firebase.storeNewsSources(
            list = list,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}