package org.sopt.jsoupsample

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:ogImage")
fun ImageView.setOgImage(ogImageUrl: String?) {
    Glide.with(this.context)
        .load(ogImageUrl)
        .placeholder(R.drawable.rectangle_gray_2)
        .into(this)
}
