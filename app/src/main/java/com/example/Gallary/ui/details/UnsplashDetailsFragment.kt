package com.example.Gallary.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.Gallary.R
import com.example.Gallary.data.Unsplash_Photo_Item
import com.example.Gallary.databinding.FragmentUnsplashDetailsBinding


class UnsplashDetailsFragment : Fragment(R.layout.fragment_unsplash_details) {

    var _datailsLayout: FragmentUnsplashDetailsBinding? = null
    val datailsLayout get() = _datailsLayout!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo=arguments?.get("photo") as Unsplash_Photo_Item
        _datailsLayout= FragmentUnsplashDetailsBinding.bind(view)
        datailsLayout.apply {
            Glide.with(this@UnsplashDetailsFragment).load(photo.urls.full).centerCrop().
            listener(object:RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    detailsProgress.isVisible=false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    detailsProgress.isVisible=false
                    creatorTxv.isVisible=true
                    descriptionTxv.isVisible=photo.description!=null
                    return false
                }
            }
            ).
            transition(DrawableTransitionOptions.withCrossFade()).
                    into(detailsImg)
            descriptionTxv.text=photo.description

            if (photo.user.portfolio_url!=null){
                val uri=Uri.parse(photo.user.portfolio_url)
                val intent=Intent(Intent.ACTION_VIEW,uri)
                creatorTxv.apply {
                    text="photo by ${photo.user.name} on unsplash"
                    setOnClickListener {
                        context.startActivity(intent)
                    }
                    paint.isUnderlineText=true

                }
            }



        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _datailsLayout=null
    }


    }
