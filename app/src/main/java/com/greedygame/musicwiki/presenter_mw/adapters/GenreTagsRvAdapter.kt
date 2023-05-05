package com.greedygame.musicwiki.presenter_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import com.greedygame.musicwiki.databinding.RvGenreListItemBinding


class GenreTagsRvAdapter(
    private var genreTagsList: List<Tag>
) :
    RecyclerView.Adapter<GenreTagsRvAdapter.ViewHolder>() {


    private var listSize = if (genreTagsList.isNotEmpty()) 10 else 0
    private var listener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvGenreListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSize
    }

    fun updateTagsList(newList: List<Tag>) {
        genreTagsList = newList
        listSize = 10
        notifyDataSetChanged()
    }

    fun showSpecificItemCount(count: Int) {
        if (count == -1) listSize = genreTagsList.size else listSize = count
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

    inner class ViewHolder(binding: RvGenreListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPost: TextView = binding.tvGenreTagName
    }
}