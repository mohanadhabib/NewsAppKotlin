package com.mohanad.newsappkotlin.data.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohanad.newsappkotlin.data.model.News

@Database(entities = [News::class] , version = 1)
@TypeConverters(SavedNewsTypeConverter::class)

abstract class SavedNewsDatabase :RoomDatabase(){

    abstract val dao:SavedNewsDao

    companion object{

        private var database:SavedNewsDatabase? = null

        fun getDatabase(context: Context):SavedNewsDatabase{
            if(database == null){
               database = Room.databaseBuilder(context,SavedNewsDatabase::class.java,"Saved News Database")
                   .fallbackToDestructiveMigration()
                   .build()
            }
            return database!!
        }
    }

}