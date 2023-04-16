package com.greedygame.musicwiki.data_mw.room

import androidx.room.Entity
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Wiki

@Entity(tableName = "charts_top_tags")
data class ChartsTagModel(
    val name: String,
    val reach: String,
    val streamable: String,
    val taggings: String,
    val url: String,
    val wiki: Wiki
)