package com.greedygame.musicwiki.data_mw.repository_mw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.greedygame.musicwiki.data_mw.api_retrofit.ApiService
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import com.greedygame.musicwiki.util_mw.NetworkResult
import javax.inject.Inject

class MusicRepository @Inject constructor(private val apiService: ApiService) {

    private val _responseLiveData = MutableLiveData<NetworkResult<ChartTopTagsResponse>>()
    val responseLiveData: LiveData<NetworkResult<ChartTopTagsResponse>>
        get() = _responseLiveData


    suspend fun fetchTopTags() {
        _responseLiveData.postValue(NetworkResult.Loading())
        try {
            val response = apiService.getChartsTopTags()
            if (response.isSuccessful) {
                _responseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                _responseLiveData.postValue(NetworkResult.Error("error"))
            } else {
                _responseLiveData.postValue(NetworkResult.Error("error"))
            }
        } catch (e: Exception) {
            _responseLiveData.postValue(NetworkResult.Error("execution error"))
        }
    }
}