package com.greedygame.musicwiki.data_mw.models.tags_top_albums

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)