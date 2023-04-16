package com.greedygame.musicwiki.app_mw.genre_details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.greedygame.musicwiki.databinding.FragmentAlbumsBinding
import com.greedygame.musicwiki.presentation_mw.adapters.AlbumsAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel


class AlbumsFragment : Fragment() {

    private val viewmodelAF: SharedViewModel by activityViewModels()
    private lateinit var bindingAF: FragmentAlbumsBinding
    private lateinit var adapterAlbums: AlbumsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingAF = FragmentAlbumsBinding.inflate(layoutInflater, container, false)

        initOnCreateView()
        return bindingAF.root
    }

    private fun initOnCreateView() {
        adapterAlbums = AlbumsAdapter(ArrayList())
        bindingAF.rvUserActivities.adapter = adapterAlbums

        viewmodelAF.albumsList.observe(viewLifecycleOwner) { albumsList ->
            albumsList?.let {
                adapterAlbums.updateAlbumsList(it.albums.album)
            }
        }
    }

}