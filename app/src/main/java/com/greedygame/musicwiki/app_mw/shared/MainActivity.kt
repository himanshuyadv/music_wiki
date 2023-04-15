package com.greedygame.musicwiki.app_mw.shared_screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.presentation_mw.lifecycle_observers.MainActLifecycle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MainActLifecycle())
    }
}