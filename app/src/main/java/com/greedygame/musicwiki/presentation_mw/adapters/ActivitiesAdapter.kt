package com.greedygame.musicwiki.presentation_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.greedygame.musicwiki.databinding.RvAlbumsItemBinding


class ActivitiesAdapter(
    private val postList: ArrayList<String>
) :
    RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvAlbumsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postText = postList[position]
        with(holder) {
            tvPost.text = postText
        }
    }

    inner class ViewHolder(binding: RvAlbumsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPost: TextView = binding.tvPost
    }
}