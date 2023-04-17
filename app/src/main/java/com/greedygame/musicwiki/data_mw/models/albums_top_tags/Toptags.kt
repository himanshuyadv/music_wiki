package com.greedygame.musicwiki.data_mw.models.albums_top_tags

import com.google.gson.annotations.SerializedName

data class Toptags(
    @SerializedName("@attr")
    val attr: Attr,
    val tag: List<Tag>
)