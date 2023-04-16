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
        viewModelGDF.setLoadingState(LoadingState.LOADING)
        val viewpagerStatusAdapter = GenreDetailsTabAdapter(this)
        viePagerGenDetails = bindingGDF.viewPagerGenreItem
        viePagerGenDetails.adapter = viewpagerStatusAdapter
        viePagerGenDetails.offscreenPageLimit = 2
        viePagerGenDetails.currentItem = 0
        TabLayoutMediator(
            bindingGDF.tabLayoutGenreItems,
            bindingGDF.viewPagerGenreItem
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position]
        }.attach()


        // Getting the data of the selected item from the ViewModel and displaying it on UI
        viewModelGDF.selectedTag.observe(viewLifecycleOwner) { selectedItem ->
            selectedItem?.let {
                lifecycleScope.launch(Dispatchers.IO) {
                    bindingGDF.tagDetails = viewModelGDF.fetchTagDetails().await()
                    viewModelGDF.fetchTopTracksFromTag().await()
                    viewModelGDF.fetchTopAlbumsFromTag().await()
                    viewModelGDF.fetchTopArtistsFromTag().await()
                    viewModelGDF.setLoadingState(LoadingState.SUCCESS)
                }
            }
            // Update UI with selected item data
        }
    }
}