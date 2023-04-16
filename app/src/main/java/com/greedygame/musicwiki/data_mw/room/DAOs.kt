package com.greedygame.musicwiki.data_mw.room

import androidx.lifecycle.LiveData
import androidx.room.*

interface ChartsTopTagsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(fav: ChartsTagModel)

    @Update
    suspend fun updateTag(fav: ChartsTagModel)

    @Delete
    suspend fun deleteTag(fav: ChartsTagModel)

    @Query("SELECT * FROM charts_top_tags")
    suspend fun getAllChartsTopTags(): List<ChartsTagModel>

}