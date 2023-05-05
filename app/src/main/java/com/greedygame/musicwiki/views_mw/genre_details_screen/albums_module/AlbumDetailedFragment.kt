package com.greedygame.musicwiki.views_mw.genre_details_screen.albums_module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.greedygame.musicwiki.databinding.FragmentAlbumDetailedBinding
import com.greedygame.musicwiki.presenter_mw.adapters.AlbumsTagsRvAdapter
import com.greedygame.musicwiki.presenter_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.presenter_mw.viewmodels.ViewModelAlbumDF
import com.greedygame.musicwiki.util_mw.ProgressState
import kotlinx.coroutines.launch


class AlbumDetailedFragment : Fragment() {

    private val viewmodelADF: ViewModelAlbumDF by viewModels()
    private val viewmodelSharedADF: SharedViewModel by activityViewModels()
    private lateinit var bindingADF: FragmentAlbumDetailedBinding
    private lateinit var adapterAlbums: AlbumsTagsRvAdapter


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

        adapterAlbums = AlbumsTagsRvAdapter(ArrayList())
        bindingADF.rvAlbumTagsList.adapter = adapterAlbums

        val album = viewmodelSharedADF.selectedAlbum.value

        with(viewmodelADF) {
            lifecycleScope.launch {
                viewmodelSharedADF.setLoadingState(ProgressState.LOADING)
                viewmodelSharedADF.setToolbarTitle("")
                if (albumInfo.value == null){
                    fetchAlbumInfo(
                        album?.name!!,
                        album.artist.name
                    ).await()

                    viewmodelADF.albumTopTags.observe(viewLifecycleOwner){topTags->
                        topTags?.let {
                            adapterAlbums.updateTagsList(it.toptags.tag)
                        }
                    }


                }

                viewmodelSharedADF.setLoadingState(ProgressState.SUCCESS)
            }
        }
    }
}