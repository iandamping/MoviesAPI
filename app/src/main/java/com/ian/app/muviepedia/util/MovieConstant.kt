package com.ian.app.muviepedia.util

import androidx.recyclerview.widget.DiffUtil
import com.ian.app.muviepedia.data.model.MovieData

/**
 *
Created by Ian Damping on 02/06/2019.
Github = https://github.com/iandamping
 */
object MovieConstant {
    const val delayMillis = 3000L
    const val imageFormatter = "https://image.tmdb.org/t/p/w500"
    const val popularPagingState = "popular paging"
    const val topRatedPagingState = "top rated paging"
    const val upcomingPagingState = "upcoming paging"
    const val intentToDiscoverActivity = "discover activity"
    const val intentToDetail = "intent detal"
    const val saveUserProfile = " save user profile"
    const val prefHelperInit = " init preference helper"
    const val RequestSignIn = 2341
    const val switchBackToMain = " switching back"

    val diffCallbacks = object : DiffUtil.ItemCallback<MovieData>() {
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem.poster_path == newItem.poster_path

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem.poster_path == newItem.poster_path
    }
}