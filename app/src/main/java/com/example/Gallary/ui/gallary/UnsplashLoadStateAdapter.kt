package com.example.Gallary.ui.gallary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.Gallary.databinding.UnsplashLoadstateBinding

class UnsplashLoadStateAdapter(private val retry:()->Unit) : LoadStateAdapter<UnsplashLoadStateAdapter.viewholde>() {


    inner class viewholde(private val binding: UnsplashLoadstateBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {

                retry.invoke()
            }
        fun bind(loadState: LoadState){
            binding.apply {
                loadPrigress.isVisible=loadState is LoadState.Loading
                errorMessageTxv.isVisible=loadState is LoadState.Error
                retryBtn.isVisible=loadState is LoadState.Error
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): viewholde {

        val view = UnsplashLoadstateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return viewholde(view)


    }

    override fun onBindViewHolder(holder: viewholde, loadState: LoadState) {
        holder.bind(loadState)
    }


}


