package com.greedygame.musicwiki.presentation_mw.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.musicwiki.data_mw.api_retrofit.ApiClient
import com.greedygame.musicwiki.data_mw.models.charts_tag_info.ChartsTagInfoModel
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.ChartTopTagsResponse
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.Album
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.TagsTopAlbumsModel
import com.greedygame.musicwiki.data_mw.models.tags_top_artists.TagsTopArtistsModel
import com.greedygame.musicwiki.data_mw.models.tags_top_tracks.TagsTopTracksModel
import com.greedygame.musicwiki.util_mw.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    // loading state of UI && Network Requests
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    //genre tags list
    private val _genreTopTags = MutableLiveData<ChartTopTagsResponse>()
    val genreTopTags: LiveData<ChartTopTagsResponse> = _genreTopTags

    // selected tag
    private val _selectedTag = MutableLiveData<Tag>()
    val selectedTag: LiveData<Tag> = _selectedTag
    var lastSelectedTag:String?=null

    // selected tag Info
    private val _selectedTagInfo = MutableLiveData<ChartsTagInfoModel>()
    val selectedTagInfo: LiveData<ChartsTagInfoModel> = _selectedTagInfo

    // album list for selected tag
    private val _albumsList = MutableLiveData<TagsTopAlbumsModel>()
    val albumsList: LiveData<TagsTopAlbumsModel> = _albumsList

    // selected album
    private val _selectedAlbum = MutableLiveData<Album>()
    val selectedAlbum: LiveData<Album> = _selectedAlbum


    // artist list for selected tag
    private val _artistsList = MutableLiveData<TagsTopArtistsModel>()
    val artistsList: LiveData<TagsTopArtistsModel> = _artistsList

    // tracks list for selected tag
    private val _tracksList = MutableLiveData<TagsTopTracksModel>()
    val tracksList: LiveData<TagsTopTracksModel> = _tracksList

    // genre details data
    var isGenreDetailsDataExist =false

    // retrofit error
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // toolbar title
    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String> = _toolbarTitle

    init {
        fetchTopTags()
        //  fetchTopAlbumsFromTag()
        //  fetchTopArtistsFromTag()
    }


    fun setLoadingState(loadingState: LoadingState) = viewModelScope.launch {
        _loadingState.postValue(loadingState)
    }

    fun setToolbarTitle(title: String) {
        _toolbarTitle.postValue(title)
    }

    private fun fetchTopTags() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getChartsTopTags()
            if (response.isSuccessful) {
                _genreTopTags.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }



    fun fetchTagDetails() = viewModelScope.async(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getTagDetails(selectedTag.value?.name!!)
            if (response.isSuccessful && response.body() != null) {
                _selectedTagInfo.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }

    fun fetchTopAlbumsFromTag() = viewModelScope.async(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getTopAlbumsFromTag(selectedTag.value?.name!!)
            if (response.isSuccessful) {
                _albumsList.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }
    fun isPreviousSelectedTag():Boolean{
        return lastSelectedTag==_selectedTag.value?.name
    }

    fun setSelectedAlbum(album:Album){
        _selectedAlbum.postValue(album)
    }

    fun fetchTopArtistsFromTag() = viewModelScope.async(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getTopArtistsFromTag(selectedTag.value?.name!!)
            if (response.isSuccessful) {
                _artistsList.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }



    fun fetchTopTracksFromTag() = viewModelScope.async(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getTopTracksFromTag(selectedTag.value?.name!!)
            if (response.isSuccessful) {
                _tracksList.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }

    fun setSelectedTag(tag: Tag) {
        Log.e("TAG", "setSelectedTag: $tag")
        _selectedTag.postValue(tag)
    }
}



