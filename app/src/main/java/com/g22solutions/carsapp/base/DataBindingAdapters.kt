package com.g22solutions.carsapp.base

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

object DataBindingAdapters {

    @BindingAdapter("img")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {

        if (TextUtils.isEmpty(url)) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageResource(com.g22solutions.carsapp.R.drawable.place_holder)
            return
        }

        Picasso.get().load(url)
                .fit().centerCrop()
                .placeholder(com.g22solutions.carsapp.R.drawable.place_holder)
                .error(com.g22solutions.carsapp.R.drawable.place_holder)
                .into(imageView)

    }

    @BindingAdapter("newsDate")
    @JvmStatic
    fun convertNewsDate(textView: TextView, dateString: String){

        if (dateString.isEmpty()) {
            return
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val date = dateFormat.parse(dateString)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

        textView.text =  formatter.format(date)
    }

}