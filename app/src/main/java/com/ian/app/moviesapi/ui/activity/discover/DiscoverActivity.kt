package com.ian.app.moviesapi.ui.activity.discover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.moviesapi.R
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.paging.popular_movie.GetPopularMoviePagingViewModel
import com.ian.app.moviesapi.util.MovieConstant
import com.ian.app.moviesapi.util.MovieConstant.diffCallbacks
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.item_discover_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverActivity : AppCompatActivity(), DiscoverView {
    private val vm: GetPopularMoviePagingViewModel by viewModel()
    private lateinit var presenter: DiscoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_discover)
        presenter = DiscoverPresenter(vm).apply {
            attachView(this@DiscoverActivity, this@DiscoverActivity)
            onCreate()
        }

    }

    override fun onSuccessGetData(data: PagedList<MovieData>?) {
        shimmerGridListContainer?.stopShimmer()
        shimmerGridListContainer?.gone()
        rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_discover_movie, 2, {
            ivDiscoverMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path, this@DiscoverActivity)
        }, diffCallbacks)
    }

    override fun onFailGetData(msg: String?) {
    }

    override fun initView() {
    }

    override fun onPause() {
        super.onPause()
        shimmerGridListContainer?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmerGridListContainer?.startShimmer()
    }

}