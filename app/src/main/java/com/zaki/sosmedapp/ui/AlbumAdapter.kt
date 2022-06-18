package com.zaki.sosmedapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zaki.sosmedapp.databinding.ItemAlbumBinding
import com.zaki.sosmedapp.helper.OnItemClick
import com.zaki.sosmedapp.network.model.Album
import com.zaki.sosmedapp.network.model.Photo

class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val albums: MutableList<Album> = mutableListOf()
    lateinit var onItemClick: OnItemClick<Photo>

    fun setAlbums(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.tvAlbumName.text = album.title

            val photoAdapter = PhotoAdapter()
            binding.rvPhotos.adapter = photoAdapter
            binding.rvPhotos.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            photoAdapter.setPhotos(album.photos)

            photoAdapter.onItemClick = object : OnItemClick<Photo> {
                override fun onItemClick(data: Photo) {
                    onItemClick.onItemClick(data)
                }
            }
        }
    }
}