package com.android.example.myvacaystories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.example.myvacaystories.databinding.PostItemBinding
import com.android.example.myvacaystories.model.StoryPost

class PostsAdapter: RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<StoryPost>(){
        override fun areItemsTheSame(oldItem: StoryPost, newItem: StoryPost): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: StoryPost, newItem: StoryPost): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val diff = AsyncListDiffer(this, diffCallBack)

    var storyPosts : List<StoryPost>
        get() = diff.currentList
        set(value) = diff.submitList(value)


    class PostViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StoryPost?) {
            binding.storyPost = item
        }

        companion object{
            fun from(parent: ViewGroup): PostViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val inflatedView = PostItemBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(inflatedView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val storyPost = storyPosts[position]
        holder.bind(storyPost)
    }

    override fun getItemCount() = storyPosts.size

}


