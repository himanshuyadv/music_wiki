package com.greedygame.musicwiki.data_mw.models.album_details

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)