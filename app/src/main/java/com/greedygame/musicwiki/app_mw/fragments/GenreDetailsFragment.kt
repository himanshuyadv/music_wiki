package com.greedygame.musicwiki.app_mw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.presentation_mw.adapters.GenreDetailsTabAdapter
import com.greedygame.musicwiki.util_mw.tabTitles

class GenreDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initOnCreateView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initOnCreateView() {
        val viewpagerStatusAdapter = GenreDetailsTabAdapter(this)
        view?.findViewById<ViewPager2>(R.id.viewPagerGenreItem)!!.adapter = viewpagerStatusAdapter
        view?.findViewById<ViewPager2>(R.id.viewPagerGenreItem)!!.offscreenPageLimit = 2
        view?.findViewById<ViewPager2>(R.id.viewPagerGenreItem)!!.currentItem = 1
        TabLayoutMediator(
            view?.findViewById<TabLayout>(R.id.tabLayoutGenreItems)!!,
            view?.findViewById<ViewPager2>(R.id.viewPagerGenreItem)!!
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position]
        }.attach()
//        setupTabIcons()
//        checkContentAndPermissionStatus()
    }
}