package com.greedygame.musicwiki.app_mw.main_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.databinding.FragmentHomeBinding
import com.greedygame.musicwiki.presentation_mw.adapters.GenreTagsRvAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel
import com.greedygame.musicwiki.util_mw.LoadingState


class HomeFragment : Fragment() {

    private val viewModelSharedHF: SharedViewModel by activityViewModels()
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
        viewModelSharedHF.setToolbarTitle("musicwiki")
        viewModelSharedHF.setLoadingState(LoadingState.LOADING)
        adapterGenreTags = GenreTagsRvAdapter(ArrayList())
        bindingHF.rvGenreTags.adapter = adapterGenreTags

        // observing Tags list from shared view model
        viewModelSharedHF.genreTopTags.observe(viewLifecycleOwner) { chartTopTagsResponse ->
            chartTopTagsResponse?.let {
                adapterGenreTags.updateTagsList(it.tags.tag)
                viewModelSharedHF.setLoadingState(LoadingState.SUCCESS)
            }
        }

        // setting listener to RecyclerView adapter
        adapterGenreTags.setOnItemClickListener { selectedTag ->
            // Update ViewModel with selected item data
            viewModelSharedHF.setSelectedTag(selectedTag)
            // Navigating to DetailFragment and passing selected item data as an argument
            findNavController().navigate(R.id.action_homeFragment_to_genreDetailsFragment)
        }
    }
}