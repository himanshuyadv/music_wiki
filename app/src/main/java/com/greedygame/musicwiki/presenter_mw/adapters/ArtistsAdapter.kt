package com.greedygame.musicwiki.presenter_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygame.musicwiki.data_mw.models.tags_top_artists.Artist
import com.greedygame.musicwiki.databinding.RvAlbumsItemBinding
import com.greedygame.musicwiki.util_mw.MEDIUM_SIZE_IMG
import com.greedygame.musicwiki.util_mw.logEGlobal


class ArtistsAdapter(
    private var albumList: List<Artist>
) :
    RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvAlbumsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    fun updateAlbumsList(newList: List<Artist>) {
        albumList = newList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artistDetails = albumList[position]
        with(holder) {
            tvPostMainText.text = artistDetails.name
          //  tvPostSubText.text = ""
            logEGlobal(artistDetails.image[MEDIUM_SIZE_IMG].text)
            Glide.with(itemView.context).load(artistDetails.image[MEDIUM_SIZE_IMG].text)
                .into(ivPostThumbnail)
        }
    }

    inner class ViewHolder(binding: RvAlbumsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPostMainText: TextView = binding.postMainText
        val tvPostSubText: TextView = binding.postSubText
        val ivPostThumbnail: ImageView = binding.ivPostThumbnail
    }
}