package com.ian.app.moviesapi.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.moviesapi.data.model.MovieData

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {
    const val delayMillis = 3000L
    const val imageFormatter = "https://image.tmdb.org/t/p/w500"

    val diffCallbacks = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
            oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
            oldItem.poster_path == newItem.poster_path
    }
}