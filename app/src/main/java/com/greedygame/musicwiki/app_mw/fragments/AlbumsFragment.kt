package com.greedygame.musicwiki.app_mw.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.greedygame.musicwiki.R
import com.greedygame.musicwiki.presentation_mw.adapters.ActivitiesAdapter


class AlbumsFragment : Fragment() {

    private lateinit var recycler:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler =view.findViewById(R.id.rvUserActivities)

        val list =ArrayList<String>()
        list.add("skugci")


        val adapter =ActivitiesAdapter(list)
        recycler.adapter=adapter

        super.onViewCreated(view, savedInstanceState)
    }
}