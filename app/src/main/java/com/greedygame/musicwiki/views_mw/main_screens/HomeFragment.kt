package com.greedygame.musicwiki.views_mw.main_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.databinding.FragmentHomeBinding
import com.greedygame.musicwiki.presenter_mw.adapters.GenreTagsRvAdapter
import com.greedygame.musicwiki.presenter_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.ProgressState
import com.greedygame.musicwiki.util_mw.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModelSharedHF by activityViewModels<SharedViewModel>()
    private lateinit var bindingHF: FragmentHomeBinding
    private lateinit var adapterGenreTags: GenreTagsRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingHF = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initOnCreateHF()
        return bindingHF.root
    }

    private fun initOnCreateHF() {
        with(viewModelSharedHF) {
            setToolbarTitle("musicwiki")
            setLoadingState(ProgressState.LOADING)

            adapterGenreTags = GenreTagsRvAdapter(ArrayList())
            bindingHF.rvGenreTags.adapter = adapterGenreTags

            // observing Tags list from shared view model
            responseLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Success -> {
                        adapterGenreTags.updateTagsList(it.data?.tags?.tag!!)
                        viewModelSharedHF.setLoadingState(ProgressState.SUCCESS)
                    }
                    is NetworkResult.Error -> {viewModelSharedHF.setLoadingState(ProgressState.ERROR)}
                    is NetworkResult.Loading -> {
                        viewModelSharedHF.setLoadingState(ProgressState.LOADING)
                    }
                }

            }
        }

        // setting listener to RecyclerView adapter
        adapterGenreTags.setOnItemClickListener { selectedTag ->
            // Update ViewModel with selected item data
            viewModelSharedHF.setSelectedTag(selectedTag)
            // Navigating to DetailFragment and passing selected item data as an argument
            findNavController().navigate(R.id.action_homeFragment_to_genreDetailsFragment)
        }
        bindingHF.ivArrowBtn.setOnClickListener {
            if (it.rotation == 0F) {
                it.rotation = 180F
                adapterGenreTags.showSpecificItemCount(10)
            } else {
                it.rotation = 0F
                adapterGenreTags.showSpecificItemCount(-1)
            }
        }

    }
}