package com.greedygame.musicwiki.app_mw.genre_details_screen.albums_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.greedygame.musicwiki.databinding.FragmentAlbumDetailedBinding
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.presentation_mw.viewmodels.ViewModelAlbumDF
import com.greedygame.musicwiki.util_mw.LoadingState
import kotlinx.coroutines.launch


class AlbumDetailedFragment : Fragment() {

    private val viewmodelADF: ViewModelAlbumDF by viewModels()
    private val viewmodelSharedADF: SharedViewModel by activityViewModels()
    private lateinit var bindingADF: FragmentAlbumDetailedBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingADF = FragmentAlbumDetailedBinding.inflate(layoutInflater, container, false)
        initOnCreateView()
        return bindingADF.root
    }

    private fun initOnCreateView() {
        bindingADF.lifecycleOwner = viewLifecycleOwner
        bindingADF.viewModelAlbumADF = viewmodelADF

        val album = viewmodelSharedADF.selectedAlbum.value

        with(viewmodelADF) {
            lifecycleScope.launch {
                viewmodelSharedADF.setLoadingState(LoadingState.LOADING)
                viewmodelSharedADF.setToolbarTitle("")
                if (albumInfo.value == null) fetchAlbumInfo(
                    album?.name!!,
                    album.artist.name
                ).await()
                viewmodelSharedADF.setLoadingState(LoadingState.SUCCESS)
            }
        }
    }
}