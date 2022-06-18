package com.zaki.sosmedapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zaki.sosmedapp.R
import com.zaki.sosmedapp.databinding.ItemPhotoBinding
import com.zaki.sosmedapp.helper.OnItemClick
import com.zaki.sosmedapp.network.model.Photo

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val photos: MutableList<Photo> = mutableListOf()
    lateinit var onItemClick: OnItemClick<Photo>

    fun setPhotos(photos: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.tvPhotoName.text = photo.title

            val url = GlideUrl(photo.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build())

            Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(binding.ivPhoto)

            itemView.setOnClickListener {
                onItemClick.onItemClick(photo)
            }
        }
    }
}