package com.mohanad.newsappkotlin.data.datasource

import android.content.Context
import com.mohanad.newsappkotlin.util.SharedPreferencesConstants

abstract class UserSharedPreferences {
    companion object{

        // Getting stored userId in shared preferences
        fun getStoredId(context: Context):String?{
            return context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES,0).getString(
                SharedPreferencesConstants.USER_ID,"")
        }

        // Storing the userId in shared preferences
        fun storeId(context: Context , id:String){
            val preferences = context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES,0).edit()
            preferences.putString(SharedPreferencesConstants.USER_ID,id).apply()
        }
    }
}