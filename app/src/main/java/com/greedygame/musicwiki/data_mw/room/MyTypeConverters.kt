package com.greedygame.musicwiki.data_mw.room

import androidx.room.TypeConverter
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromListToJSON(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJSONToList(json: String): List<String> {
        return Gson().fromJson(json, Array<String>::class.java).toList()
    }

}