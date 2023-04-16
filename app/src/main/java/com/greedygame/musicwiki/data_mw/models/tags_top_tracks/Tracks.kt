package com.greedygame.musicwiki.data_mw.models.tags_top_tracks

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)