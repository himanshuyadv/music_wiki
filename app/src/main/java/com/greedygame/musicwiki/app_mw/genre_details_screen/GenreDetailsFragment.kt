package com.greedygame.musicwiki.app_mw.genre_details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.greedygame.musicwiki.databinding.FragmentGenreDetailsBinding
import com.greedygame.musicwiki.presentation_mw.adapters.GenreDetailsTabAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.LoadingState
import com.greedygame.musicwiki.util_mw.tabTitles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GenreDetailsFragment : Fragment() {
    private lateinit var bindingGDF: FragmentGenreDetailsBinding
    private val viewModelGDF: SharedViewModel by activityViewModels()
    private lateinit var viePagerGenDetails: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingGDF = FragmentGenreDetailsBinding.inflate(layoutInflater, container, false)
        initOnCreateView()
        return bindingGDF.root
    }

    private fun initOnCreateView() {
        viewModelGDF.setToolbarTitle("")
        if (!viewModelGDF.isPreviousSelectedTag()) viewModelGDF.setLoadingState(LoadingState.LOADING)
        val viewpagerStatusAdapter = GenreDetailsTabAdapter(this)
        viePagerGenDetails = bindingGDF.viewPagerGenreItem
        viePagerGenDetails.adapter = viewpagerStatusAdapter
        viePagerGenDetails.offscreenPageLimit = 2
        viePagerGenDetails.currentItem = 0



        with(bindingGDF) {
            TabLayoutMediator(
                tabLayoutGenreItems,
                viewPagerGenreItem
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = tabTitles[position]
            }.attach()

            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModelGDF

            appbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                // Checking if the appbar is fully collapsed
                if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange) {
                    // appbar is fully collapsed
                    viewModelGDF.setToolbarTitle(viewModelGDF.selectedTag.value?.name.toString())
                } else if (verticalOffset == 0) {
                    // appbar is fully expanded
                } else {
                    // appbar is partially collapsed
                    viewModelGDF.setToolbarTitle("")
                }
            }
        }

        // Getting the data of the selected item from the ViewModel and displaying it on UI
        viewModelGDF.selectedTag.value?.let {
            lifecycleScope.launch(Dispatchers.IO) {
                with(viewModelGDF) {
                    if (isPreviousSelectedTag()) return@launch
                    lastSelectedTag = it.name
                    fetchTagDetails().await()
                    fetchTopTracksFromTag().await()
                    fetchTopAlbumsFromTag().await()
                    fetchTopArtistsFromTag().await()
                    setLoadingState(LoadingState.SUCCESS)
                }
            }
        }
    }
}