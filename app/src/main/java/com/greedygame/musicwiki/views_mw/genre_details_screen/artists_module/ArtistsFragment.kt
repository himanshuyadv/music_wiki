package com.greedygame.musicwiki.views_mw.genre_details_screen.artists_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.greedygame.musicwiki.databinding.FragmentAlbumsBinding
import com.greedygame.musicwiki.presenter_mw.adapters.ArtistsAdapter
import com.greedygame.musicwiki.presenter_mw.viewmodels.SharedViewModel


class ArtistsFragment : Fragment() {
    private val viewmodelArtFrag: SharedViewModel by activityViewModels()
    private lateinit var bindingArtFrag: FragmentAlbumsBinding
    private lateinit var adapterArtists: ArtistsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingArtFrag = FragmentAlbumsBinding.inflate(layoutInflater, container, false)
        initOnCreateView()
        return bindingArtFrag.root
    }

    private fun initOnCreateView() {
        adapterArtists = ArtistsAdapter(ArrayList())
        bindingArtFrag.rvUserActivities.adapter = adapterArtists

        viewmodelArtFrag.artistsList.observe(viewLifecycleOwner) { albumsList ->
            albumsList?.let {
                adapterArtists.updateAlbumsList(it.topartists.artist)
            }
        }
    }


}