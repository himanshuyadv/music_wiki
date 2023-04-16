package com.greedygame.musicwiki.util_mw

sealed class LoadingState {
    object LOADING : LoadingState()
    object SUCCESS : LoadingState()
    object ERROR : LoadingState()
}