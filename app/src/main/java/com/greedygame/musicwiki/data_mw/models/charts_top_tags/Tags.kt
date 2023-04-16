package com.greedygame.musicwiki.data_mw.models.charts_top_tags

import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("@attr")
    val attr: Attr,
    val tag: List<Tag>
)