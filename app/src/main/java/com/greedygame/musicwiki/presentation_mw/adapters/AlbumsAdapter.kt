package com.greedygame.musicwiki.presentation_mw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygame.musicwiki.data_mw.models.charts_top_tags.Tag
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.Album
import com.greedygame.musicwiki.data_mw.models.tags_top_albums.Albums

import com.greedygame.musicwiki.databinding.RvAlbumsItemBinding
import com.greedygame.musicwiki.util_mw.MEDIUM_SIZE_IMG


class AlbumsAdapter(
    private var albumList: List<Album>
) :
    RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {



    private var listenerAlbumClick: ((Album) -> Unit)? = null

    fun setOnItemClickListener(listener: (Album) -> Unit) {
        listenerAlbumClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvAlbumsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    fun updateAlbumsList(newList: List<Album>) {
        albumList = newList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val albumDetails = albumList[position]
        with(holder) {
            tvPostMainText.text = albumDetails.name
            tvPostSubText.text =albumDetails.artist.name
            Glide.with(itemView.context).load(albumDetails.image[MEDIUM_SIZE_IMG].text).into(ivPostThumbnail)
            itemView.setOnClickListener {
                listenerAlbumClick?.invoke(albumDetails)
            }
        }
    }

    inner class ViewHolder(binding: RvAlbumsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvPostMainText: TextView = binding.postMainText
        val tvPostSubText: TextView = binding.postSubText
        val ivPostThumbnail:ImageView = binding.ivPostThumbnail
    }
}