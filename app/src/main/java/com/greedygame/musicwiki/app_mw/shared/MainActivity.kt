package com.greedygame.musicwiki.app_mw.shared

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.greedygame.musicwiki.databinding.ActivityMainBinding
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.LoadingState

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainAct: ActivityMainBinding
    private val viewModelSharedMainAct: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainAct.root)
        //  lifecycle.addObserver(MainActLifecycle())




        viewModelSharedMainAct.loadingState.observe(this) { loadingState ->
            when (loadingState) {
                LoadingState.LOADING -> {
                    // Show progress bar
                    bindingMainAct.progressLayout.visibility = VISIBLE
                }
                LoadingState.SUCCESS -> {
                    // Hide progress bar
                    bindingMainAct.progressLayout.visibility = GONE
                }
                LoadingState.ERROR -> {
                    // Show error prompt
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                    // Hide progress bar
                    bindingMainAct.progressLayout.visibility = GONE
                }
            }
        }
        viewModelSharedMainAct.toolbarTitle.observe(this){title->
            bindingMainAct.tvToolbarTitle.text=title
        }
    }
}