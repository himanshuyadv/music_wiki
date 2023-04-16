package com.greedygame.musicwiki.data_mw.api_retrofit

import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=chart.gettoptags")
    suspend fun getChartsTopTags(
        @Query("api_key") apiKey: String = "76de6f9b44f2e0ea14ffa19ce656347e",
        @Query("format") format: String = "json"
    ): Response<ChartTopTagsResponse>
}
