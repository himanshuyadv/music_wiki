package com.greedygame.musicwiki.presentation_mw.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.greedygame.musicwiki.app_mw.fragments.AlbumsFragment
import com.greedygame.musicwiki.util_mw.tabTitles

class GenreDetailsTabAdapter(fragmentActivity: Fragment) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AlbumsFragment()
            1 -> return AlbumsFragment()
            2 -> return AlbumsFragment()
        }
        return AlbumsFragment()
    }

    override fun getItemCount(): Int {
        return tabTitles.size
    }
}