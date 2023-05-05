package com.greedygame.musicwiki.presenter_mw.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.greedygame.musicwiki.views_mw.genre_details_screen.albums_module.AlbumsFragment
import com.greedygame.musicwiki.views_mw.genre_details_screen.artists_module.ArtistsFragment
import com.greedygame.musicwiki.views_mw.genre_details_screen.tracks_module.TracksFragment
import com.greedygame.musicwiki.util_mw.tabTitles

class GenreDetailsTabAdapter(fragmentActivity: Fragment) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AlbumsFragment()
            1 -> return ArtistsFragment()
            2 -> return TracksFragment()
        }
        return AlbumsFragment()
    }

    override fun getItemCount(): Int {
        return tabTitles.size
    }
}