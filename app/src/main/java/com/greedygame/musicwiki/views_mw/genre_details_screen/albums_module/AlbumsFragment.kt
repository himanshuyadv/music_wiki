package com.greedygame.musicwiki.views_mw.genre_details_screen.albums_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.databinding.FragmentAlbumsBinding
import com.greedygame.musicwiki.presenter_mw.adapters.AlbumsAdapter
import com.greedygame.musicwiki.presenter_mw.viewmodels.SharedViewModel


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

        // setting listener to RecyclerView adapter
        adapterAlbums.setOnItemClickListener { selectedAlbum ->

            viewmodelAF.setSelectedAlbum(selectedAlbum)
            // Navigating to DetailFragment and passing selected item data as an argument
            findNavController().navigate(R.id.action_genreDetailsFragment_to_albumDetailedFragment)
        }
    }
}