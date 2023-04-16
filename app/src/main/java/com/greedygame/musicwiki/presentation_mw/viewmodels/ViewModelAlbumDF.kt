package com.greedygame.musicwiki.presentation_mw.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.musicwiki.data_mw.api_retrofit.ApiClient
import com.greedygame.musicwiki.data_mw.models.album_details.AlbumInfoModel
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.Album
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.Albums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewModelAlbumDF : ViewModel() {




    // selected album Info
    private val _albumInfo = MutableLiveData<AlbumInfoModel>()
    val albumInfo: LiveData<AlbumInfoModel> = _albumInfo

    // retrofit error
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage





     fun fetchAlbumInfo(albumName:String,artistName:String) = viewModelScope.async(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getAlbumInfo(albumName,artistName)
            if (response.isSuccessful) {
                _albumInfo.postValue(response.body())
            } else {
                _errorMessage.postValue(response.message())
            }
        } catch (e: Exception) {
            _errorMessage.postValue(e.message)
        }
    }
}