package com.ian.app.moviesapi.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ian.app.helper.util.*
import com.ian.app.moviesapi.R
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.viewmodel.GetHomeMovieViewModel
import com.ian.app.moviesapi.ui.activity.detail.DetailActivity
import com.ian.app.moviesapi.ui.activity.discover.DiscoverActivity
import com.ian.app.moviesapi.ui.fragment.home.slideradapter.SliderItemAdapter
import com.ian.app.moviesapi.util.MovieConstant.delayMillis
import com.ian.app.moviesapi.util.MovieConstant.imageFormatter
import com.ian.app.moviesapi.util.MovieConstant.intentToDetail
import com.ian.app.moviesapi.util.MovieConstant.intentToDiscoverActivity
import com.ian.app.moviesapi.util.MovieConstant.popularPagingState
import com.ian.app.moviesapi.util.MovieConstant.topRatedPagingState
import com.ian.app.moviesapi.util.MovieConstant.upcomingPagingState
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeView {
    private var actualView: View? = null


    private val vm: GetHomeMovieViewModel by viewModel()
    private lateinit var presenter: HomePresenter
    private var mHandler: Handler? = null
    private var pageSize: Int? = 0
    private var currentPage = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = HomePresenter(vm).apply {
            attachView(this@HomeFragment, this@HomeFragment)
            onAttach()
        }
        mHandler = Handler()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = container?.inflates(R.layout.fragment_home)
        views?.let { presenter.onCreateView(it) }
        return views
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

    override fun initView(view: View) {
        this.actualView = view
        view.tvSeeAllPopularMovie.setOnClickListener {
            context?.startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, popularPagingState)
            }
        }
        view.tvSeeAllTopRatedMovie.setOnClickListener {
            context?.startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, topRatedPagingState)
            }
        }
        view.tvSeeAllUpComingMovie.setOnClickListener {
            context?.startActivity<DiscoverActivity> {
                putExtra(intentToDiscoverActivity, upcomingPagingState)
            }
        }
    }

    override fun onSuccessGetPopularMovie(data: List<MovieData>?) {
        actualView?.shimmerHome?.stopShimmer()
        actualView?.shimmerHome?.gone()
        actualView?.rvPopularMovie?.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, context)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onSuccessGetNowPlayingMovie(data: List<MovieData>?) {
        pageSize = data?.size
        actualView?.vpNowPlaying?.adapter = data?.let { SliderItemAdapter(it, context) }
        actualView?.indicator?.setViewPager(vpNowPlaying)
        if (mHandler != null) {
            mHandler?.removeCallbacks(slideRunnable)
        }
        mHandler?.postDelayed(slideRunnable, delayMillis)
    }

    override fun onSuccessGetTopRatedMovie(data: List<MovieData>?) {
        actualView?.shimmerHome?.stopShimmer()
        actualView?.shimmerHome?.gone()
        actualView?.rvTopRatedMovie?.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, context)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onSuccessGetUpComingMovie(data: List<MovieData>?) {
        actualView?.shimmerHome?.stopShimmer()
        actualView?.shimmerHome?.gone()
        actualView?.rvUpComingMovie?.setUpHorizontal(data, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(imageFormatter + it.poster_path, context)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onFailGetData(msg: String?) {
        logE(msg)
        actualView?.shimmerHome?.stopShimmer()
        actualView?.shimmerHome?.gone()
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
        actualView?.shimmerHome?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        actualView?.shimmerHome?.startShimmer()
    }
}
