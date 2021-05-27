package com.example.Gallary.ui.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.Gallary.R
import com.example.Gallary.data.Unsplash_Photo_Item
import com.example.Gallary.databinding.UsplashItemBinding

class UnsplashAdapter : PagingDataAdapter<Unsplash_Photo_Item, UnsplashAdapter.Viewholder>(utils) {

    var onItemClicked: ((Unsplash_Photo_Item) -> Unit)? = null


    inner class Viewholder(private val binding: UsplashItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClicked?.invoke(getItem(adapterPosition)!!)

            }}
            fun bind(item: Unsplash_Photo_Item) {
                binding.apply {
                    Glide.with(itemView).load(item.urls.regular).centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade()).
                        error(R.drawable.error_24).into(imageView)
                    usernameTxv.text = item.user.username



            }

        }

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val currentItem=getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = UsplashItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(view)

    }

    companion object {
        val utils = object : DiffUtil.ItemCallback<Unsplash_Photo_Item>() {
            override fun areItemsTheSame(
                oldItem: Unsplash_Photo_Item,
                newItem: Unsplash_Photo_Item
            ): Boolean {
               return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Unsplash_Photo_Item,
                newItem: Unsplash_Photo_Item
            ): Boolean {
                return oldItem==newItem
            }
        }
    }
}