package com.greedygame.musicwiki.app_mw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tags
import com.greedygame.musicwiki.presentation_mw.adapters.GenreTagsRvAdapter
import com.greedygame.musicwiki.presentation_mw.viewmodels.SharedViewModel


class HomeFragment : Fragment() {
    private val sharedViewModelHF: SharedViewModel by viewModels()
    private val adapterGenreTags by lazy { GenreTagsRvAdapter(ArrayList()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  findNavController().navigate(R.id.action_homeFragment_to_genreDetailsFragment
        val rv = view.findViewById<RecyclerView>(R.id.rvGenreTags)
        rv.adapter =adapterGenreTags

        sharedViewModelHF.genreTopTags.observe(requireActivity()) {
            rv.adapter=GenreTagsRvAdapter(it.tags.tag)
        }



        super.onViewCreated(view, savedInstanceState)
    }


}