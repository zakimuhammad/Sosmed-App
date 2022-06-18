package com.zaki.sosmedapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaki.sosmedapp.databinding.ItemPostBinding
import com.zaki.sosmedapp.helper.OnItemClick
import com.zaki.sosmedapp.network.model.Post

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts: MutableList<Post> = mutableListOf()
    lateinit var onItemClick: OnItemClick<Post>

    fun setPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding) {
            tvPostTitle.text = post.title
            tvPostDesc.text = post.body
            tvUserName.text = post.user.username
            tvCompanyAme.text = post.user.company.name

            itemView.setOnClickListener {
                onItemClick.onItemClick(post)
            }
        }
    }
}