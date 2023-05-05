package com.greedygame.musicwiki.views_mw.main_screens

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.greedygame.musicwiki.databinding.ActivityMainBinding
import com.greedygame.musicwiki.presenter_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.ProgressState
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


        viewModelSharedMainAct.progressState.observe(this@MainActivity) { loadingState ->
            updateProgressBarState(loadingState)
        }
        viewModelSharedMainAct.toolbarTitle.observe(this@MainActivity) { title ->
            bindingMainAct.tvToolbarTitle.text = title
        }
        bindingMainAct.ivBackBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateProgressBarState(state: ProgressState) {
        when (state) {
            ProgressState.LOADING -> {
                // show the progress bar
                bindingMainAct.progressLayout.visibility = VISIBLE
            }
            ProgressState.SUCCESS -> {
                // hide the progress bar
                bindingMainAct.progressLayout.visibility = GONE
            }
            ProgressState.ERROR -> {
                // show an error message and hide the progress bar
                bindingMainAct.progressLayout.visibility = GONE
                //   errorMessage.visibility = VISIBLE
            }
        }
    }
}