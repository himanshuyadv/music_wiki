package com.greedygame.musicwiki.data_mw.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database([ChartsTagModel::class], version = 1)
abstract class DatabaseMW:RoomDatabase() {

    abstract fun getChartsTopTags():ChartsTopTagsDao


    companion object {
        @Volatile
        private var INSTANCE: DatabaseMW? = null

        fun getDatabase(context: Context): DatabaseMW {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseMW::class.java,
                    context.packageName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}