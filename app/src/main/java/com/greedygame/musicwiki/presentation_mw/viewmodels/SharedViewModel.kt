package com.greedygame.musicwiki.presentation_mw.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.musicwiki.data_mw.api_retrofit.ApiClient
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _users = MutableLiveData<ChartTopTagsResponse>()
    val users: LiveData<ChartTopTagsResponse> = _users

    init {
        viewModelScope.launch {
            val response = ApiClient.apiService.getChartsTopTags()
            if (response.isSuccessful) {
                _users.value = response.body()
            } else {
                // handle error
            }
        }
    }
}