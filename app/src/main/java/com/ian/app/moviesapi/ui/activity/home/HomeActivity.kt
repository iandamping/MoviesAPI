package com.ian.app.moviesapi.ui.activity.home

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.helper.util.*
import com.ian.app.moviesapi.R
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.viewmodel.GetHomeMovieViewModel
import com.ian.app.moviesapi.ui.activity.detail.DetailActivity
import com.ian.app.moviesapi.ui.activity.discover.DiscoverActivity
import com.ian.app.moviesapi.ui.activity.home.slideradapter.SliderItemAdapter
import com.ian.app.moviesapi.util.MovieConstant.delayMillis
import com.ian.app.moviesapi.util.MovieConstant.imageFormatter
import com.ian.app.moviesapi.util.MovieConstant.intentToDetail
import com.ian.app.moviesapi.util.MovieConstant.intentToDiscoverActivity
import com.ian.app.moviesapi.util.MovieConstant.popularPagingState
import com.ian.app.moviesapi.util.MovieConstant.topRatedPagingState
import com.ian.app.moviesapi.util.MovieConstant.upcomingPagingState
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeView {


    private val vm: GetHomeMovieViewModel by viewModel()
    private lateinit var presenter: HomePresenter
    private var mHandler: Handler? = null
    private var pageSize: Int? = 0
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(vm).apply {
            attachView(this@HomeActivity, this@HomeActivity)
            onCreate()
        }
        mHandler = Handler()
    }

    private var slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            vpNowPlaying?.setCurrentItem(currentPage++, true)
            mHandler?.postDelayed(this, delayMillis)
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
        }, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onSuccessGetNowPlayingMovie(data: List<MovieData>?) {
        pageSize = data?.size
        vpNowPlaying?.adapter = data?.let { SliderItemAdapter(it, this@HomeActivity) }
        indicator?.setViewPager(vpNowPlaying)
        if (mHandler != null) {
            mHandler?.removeCallbacks(slideRunnable)
        }
        mHandler?.postDelayed(slideRunnable, delayMillis)
    }

    override fun onSuccessGetTopRatedMovie(data: List<MovieData>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        rvTopRatedMovie.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@HomeActivity)
            }
        }, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
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
        }, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onFailGetData(msg: String?) {
        logE(msg)
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
    }

    override fun initView() {
        tvSeeAllPopularMovie.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, popularPagingState)
            }
        }
        tvSeeAllTopRatedMovie.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, topRatedPagingState)
            }
        }
        tvSeeAllUpComingMovie.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, upcomingPagingState)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mHandler?.postDelayed(slideRunnable, delayMillis)
    }

    override fun onStop() {
        super.onStop()
        mHandler?.removeCallbacks(slideRunnable)
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
