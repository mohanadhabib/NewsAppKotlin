package com.mohanad.newsappkotlin.data.datasource.sharedpreferences

import android.content.Context
import com.mohanad.newsappkotlin.util.SharedPreferencesConstants
// User stored id shared preferences class
class UserSharedPreferences {
    // Getting stored userId in shared preferences
    fun getStoredId(context: Context): String? {
        return context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES, 0)
            .getString(
                SharedPreferencesConstants.USER_ID, ""
            )
    }
    // Storing the userId in shared preferences
    fun storeId(context: Context, id: String) {
        val preferences =
            context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES, 0)
                .edit()
        preferences.putString(SharedPreferencesConstants.USER_ID, id).apply()
    }
}