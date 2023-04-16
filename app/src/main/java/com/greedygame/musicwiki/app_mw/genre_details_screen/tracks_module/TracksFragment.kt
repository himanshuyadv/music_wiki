package com.greedygame.musicwiki.app_mw.genre_details_screen.tracks_module

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.greedygame.musicwiki.databinding.FragmentAlbumsBinding
import com.greedygame.musicwiki.presentation_mw.adapters.TracksAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel


class TracksFragment : Fragment() {

    private val viewmodelTF: SharedViewModel by activityViewModels()
    private lateinit var bindingTF: FragmentAlbumsBinding
    private lateinit var adapterTracks: TracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingTF = FragmentAlbumsBinding.inflate(layoutInflater, container, false)
        initOnCreateView()
        return bindingTF.root
    }

    private fun initOnCreateView() {
        adapterTracks = TracksAdapter(ArrayList())
        bindingTF.rvUserActivities.adapter = adapterTracks

        viewmodelTF.tracksList.observe(viewLifecycleOwner) { albumsList ->
            albumsList?.let {
                adapterTracks.updateTracksList(it.tracks.track)
            }
        }
    }
}