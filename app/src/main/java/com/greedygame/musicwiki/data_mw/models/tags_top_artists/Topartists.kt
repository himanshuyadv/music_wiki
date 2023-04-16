package com.greedygame.musicwiki.data_mw.models.tags_top_artists

import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("attr")
    val attr: Attr,
    val artist: List<Artist>
)