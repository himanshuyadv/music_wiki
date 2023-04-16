package com.greedygame.musicwiki.presentation_mw.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.musicwiki.data_mw.api_retrofit.ApiClient
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    //genre tags list
    private val _genreTopTags = MutableLiveData<ChartTopTagsResponse>()
    val genreTopTags: LiveData<ChartTopTagsResponse> = _genreTopTags

    // selected tag details
    private val _selectedItem = MutableLiveData<Tag>()
    val selectedItem: LiveData<Tag> = _selectedItem

    // retrofit error
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchTopTags()
    }

    private fun fetchTopTags() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getChartsTopTags()
                if (response.isSuccessful) {
                    _genreTopTags.value = response.body()
                } else {
                    _errorMessage.value = response.message()
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
    fun setSelectedTag(tag: Tag) {
        _selectedItem.value = tag
    }
}



