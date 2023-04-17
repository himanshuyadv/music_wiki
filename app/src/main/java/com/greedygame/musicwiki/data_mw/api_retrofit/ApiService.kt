package com.greedygame.musicwiki.data_mw.api_retrofit

import com.greedygame.musicwiki.BuildConfig
import com.greedygame.musicwiki.data_mw.models.album_details.AlbumInfoModel
import com.greedygame.musicwiki.data_mw.models.albums_top_tags.AlbumsTopTagsModel
import com.greedygame.musicwiki.data_mw.models.charts_tag_info.ChartsTagInfoModel
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.TagsTopAlbumsModel
import com.greedygame.musicwiki.data_mw.models.tags_top_artists.TagsTopArtistsModel
import com.greedygame.musicwiki.data_mw.models.tags_top_tracks.TagsTopTracksModel
import com.greedygame.musicwiki.util_mw.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=chart.gettoptags")
    suspend fun getChartsTopTags(
        @Query("api_key") apiKey: String = "76de6f9b44f2e0ea14ffa19ce656347e",
        @Query("format") format: String = "json"
    ): Response<ChartTopTagsResponse>

    @GET(GET_TAG_INFO)
    suspend fun getTagDetails(
        @Query("tag") tag: String = "rock"
    ): Response<ChartsTagInfoModel>

    @GET(GET_TAG_TOP_ALBUMS)
    suspend fun getTopAlbumsFromTag(
        @Query("tag") tag: String = "rock"
    ): Response<TagsTopAlbumsModel>

    @GET(GET_TAG_TOP_ARTISTS)
    suspend fun getTopArtistsFromTag(
        @Query("tag") tag: String = "rock"
    ): Response<TagsTopArtistsModel>

    @GET(GET_TAG_TOP_TRACKS)
    suspend fun getTopTracksFromTag(
        @Query("tag") tag: String = "rock"
    ): Response<TagsTopTracksModel>

    @GET(GET_ALBUM_INFO)
    suspend fun getAlbumInfo(
        @Query("album") albumName: String = "Believe",
        @Query("artist") artist: String = "Cher"
    ): Response<AlbumInfoModel>

    @GET(GET_ALBUM_TOP_TAGS)
    suspend fun getAlbumTopTags(
        @Query("album") albumName: String = "Believe",
        @Query("artist") artist: String = "Cher"
    ): Response<AlbumsTopTagsModel>


//    companion object {
//        private const val API_KEY = BuildConfig.lastFmApiKey
//    }
}
