package com.greedygame.musicwiki.presentation_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import com.greedygame.musicwiki.databinding.GenreListItemBinding


class GenreTagsRvAdapter(
    private var genreTagsList: List<Tag>
) :
    RecyclerView.Adapter<GenreTagsRvAdapter.ViewHolder>() {

    private var listener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            GenreListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return genreTagsList.size
    }

    fun updateTagsList(newList: List<Tag>) {
        genreTagsList = newList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tagDetails = genreTagsList[position]
        with(holder) {
            tvPost.text = tagDetails.name
            holder.itemView.setOnClickListener {
                listener?.invoke(tagDetails)
            }
        }
    }

    inner class ViewHolder(binding: GenreListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPost: TextView = binding.tvGenreTagName
    }
}