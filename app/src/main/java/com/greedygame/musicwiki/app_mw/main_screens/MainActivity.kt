package com.greedygame.musicwiki.app_mw.main_screens

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.greedygame.musicwiki.databinding.ActivityMainBinding
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.LoadingState
import com.greedygame.musicwiki.util_mw.toastActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainAct: ActivityMainBinding
    private val viewModelSharedMainAct: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainAct.root)
        //  lifecycle.addObserver(MainActLifecycle())


        with(bindingMainAct) {
            viewModelSharedMainAct.loadingState.observe(this@MainActivity) { loadingState ->
                when (loadingState) {
                    LoadingState.LOADING -> {
                        // Show progress bar
                        progressLayout.visibility = VISIBLE
                    }
                    LoadingState.SUCCESS -> {
                        // Hide progress bar
                        progressLayout.visibility = GONE
                    }
                    LoadingState.ERROR -> {
                        // Show error prompt
                        toastActivity("Something went wrong")
                        // Hide progress bar
                        progressLayout.visibility = GONE
                    }
                }
            }
            viewModelSharedMainAct.toolbarTitle.observe(this@MainActivity) { title ->
                tvToolbarTitle.text = title
            }
            bindingMainAct.ivBackBtn.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }

    }
}