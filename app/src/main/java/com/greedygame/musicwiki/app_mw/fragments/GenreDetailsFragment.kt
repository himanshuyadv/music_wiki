package com.greedygame.musicwiki.app_mw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.greedygame.musicwiki.databinding.FragmentGenreDetailsBinding
import com.greedygame.musicwiki.presentation_mw.adapters.GenreDetailsTabAdapter
import com.greedygame.musicwiki.util_mw.tabTitles

class GenreDetailsFragment : Fragment() {
    private lateinit var bindingGDF: FragmentGenreDetailsBinding
    private lateinit var viePagerGenDetails: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingGDF = FragmentGenreDetailsBinding.inflate(layoutInflater, container, false)
        return bindingGDF.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initOnCreateView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initOnCreateView() {
        val viewpagerStatusAdapter = GenreDetailsTabAdapter(this)
        viePagerGenDetails = bindingGDF.viewPagerGenreItem
        viePagerGenDetails.adapter = viewpagerStatusAdapter
        viePagerGenDetails.offscreenPageLimit = 2
        viePagerGenDetails.currentItem = 1
        TabLayoutMediator(
            bindingGDF.tabLayoutGenreItems,
            bindingGDF.viewPagerGenreItem
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position]
        }.attach()
//        setupTabIcons()
//        checkContentAndPermissionStatus()
    }
}