package com.greedygame.musicwiki.data_mw.models.tags_top_tracks

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val text: String,
    val fulltrack: String
)