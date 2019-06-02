package com.ian.app.moviesapi.ui.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.helper.util.logE
import com.ian.app.moviesapi.R
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.viewmodel.GetHomeMovieViewModel
import com.ian.app.moviesapi.util.MovieConstant.imageFormatter
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeView {
    private val vm: GetHomeMovieViewModel by viewModel()
    private lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(vm).apply {
            attachView(this@HomeActivity, this@HomeActivity)
            onCreate()
        }
    }

    override fun onSuccessGetPopularMovie(data: List<MovieData>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        rvPopularMovie.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@HomeActivity)
            }
        })
    }

    override fun onSuccessGetNowPlayingMovie(data: List<MovieData>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        rvNowPlayingMovie.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@HomeActivity)
            }
        })
    }

    override fun onSuccessGetTopRatedMovie(data: List<MovieData>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        rvTopRatedMovie.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@HomeActivity)
            }
        })
    }

    override fun onSuccessGetUpComingMovie(data: List<MovieData>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        rvUpComingMovie.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@HomeActivity)
            }
        })
    }

    override fun onFailGetData(msg: String?) {
        logE(msg)
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
    }

    override fun initView() {
    }

    override fun onPause() {
        super.onPause()
        shimmerHome?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmerHome?.startShimmer()
    }
}
