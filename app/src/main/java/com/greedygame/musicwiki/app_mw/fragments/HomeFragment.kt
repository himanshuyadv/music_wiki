package com.greedygame.musicwiki.app_mw.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.presentation_mw.adapters.GenreTagsRvAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel


class HomeFragment : Fragment() {

    private val sharedViewModelHF: SharedViewModel by viewModels()
    private lateinit var adapterGenreTags: GenreTagsRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapterGenreTags = GenreTagsRvAdapter(ArrayList())
        val rv = view.findViewById<RecyclerView>(R.id.rvGenreTags)
        rv.adapter = adapterGenreTags

        sharedViewModelHF.genreTopTags.observe(viewLifecycleOwner) { chartTopTagsResponse ->
            chartTopTagsResponse?.let {
                adapterGenreTags.updateTagsList(it.tags.tag)
            }
        }

        // listener for RecyclerView adapter
        adapterGenreTags.setOnItemClickListener { selectedTag ->

            // Update ViewModel with selected item data
            sharedViewModelHF.setSelectedTag(selectedTag)

            Log.e("TAG", "Item clicked: $selectedTag")

            // Navigating to DetailFragment and passing selected item data as an argument
            findNavController().navigate(R.id.action_homeFragment_to_genreDetailsFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }


}