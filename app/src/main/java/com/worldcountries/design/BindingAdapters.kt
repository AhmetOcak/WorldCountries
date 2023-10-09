package com.worldcountries.design

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.load(url)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ShapeableImageView, url: String) {
    view.load(url)
}