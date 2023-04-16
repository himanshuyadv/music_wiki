package com.greedygame.musicwiki.presentation_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygame.musicwiki.data_mw.models.tags_top_tracks.Track
import com.greedygame.musicwiki.databinding.RvAlbumsItemBinding
import com.greedygame.musicwiki.util_mw.MEDIUM_SIZE_IMG
import com.greedygame.musicwiki.util_mw.SMALL_SIZE_IMG


class TracksAdapter(
    private var tracksList: List<Track>
) :
    RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvAlbumsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    fun updateTracksList(newList: List<Track>) {
        tracksList = newList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return tracksList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tracksDetails = tracksList[position]
        with(holder) {
            tvPostMainText.text = tracksDetails.name
            tvPostSubText.text = tracksDetails.artist.name
            Glide.with(itemView.context).load(tracksDetails.image[MEDIUM_SIZE_IMG].text).into(ivPostThumbnail)
        }
    }

    inner class ViewHolder(binding: RvAlbumsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPostMainText: TextView = binding.postMainText
        val tvPostSubText: TextView = binding.postSubText
        val ivPostThumbnail: ImageView = binding.ivPostThumbnail
    }
}