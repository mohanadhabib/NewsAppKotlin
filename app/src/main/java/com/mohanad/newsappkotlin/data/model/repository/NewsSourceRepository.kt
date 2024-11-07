package com.mohanad.newsappkotlin.data.model.repository

import com.mohanad.newsappkotlin.data.datasource.firebase.NewsSourceFirebase
import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsSourceApi
import com.mohanad.newsappkotlin.data.datasource.retrofit.NewsSourceRetrofit
import com.mohanad.newsappkotlin.data.model.NewsSource
import com.mohanad.newsappkotlin.data.model.NewsSourceResponse

class NewsSourceRepository {

    // get all news sources from the restApi
    suspend fun getNewsSource(apiKey: String , onFailure:(Exception)->Unit):NewsSourceResponse?{
        return try {
            NewsSourceRetrofit.getRetrofit()
                ?.create(NewsSourceApi::class.java)
                ?.getNewsSource(apiKey)
        }catch (e:Exception){
            onFailure(e)
            null
        }
    }

    // get only the searched news source from the complete list
    fun getSearchedNewsSource(title:String ,allNewsList:List<NewsSource> ):List<NewsSource>{
        val list = mutableListOf<NewsSource>()
        allNewsList.forEach {
            if(it.name.contains(title)){
                list.add(it)
            }
        }
        return list
    }

    // store user's preferred news sources into firebase firestore
    fun storeNewsSources(list: List<String> , onSuccess:(Void?) -> Unit , onFailure: (Exception) -> Unit ){
        NewsSourceFirebase.storeNewsSources(
            list = list,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}