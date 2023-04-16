package com.greedygame.musicwiki.app_mw.shared

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.presentation_mw.lifecycle_observers.MainActLifecycle
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.TAG

class MainActivity : AppCompatActivity() {
    private val sharedViewModelMainAct: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MainActLifecycle())

        sharedViewModelMainAct.users.observe(this){
            Log.e(TAG, "onCreate: $it ")
        }

    }
}