package com.ian.app.muviepedia.ui.fragment.movie

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ian.app.helper.util.*
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseFragment
import com.ian.app.muviepedia.base.BaseState
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetHomeMoviesData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.viewmodel.movie.GetHomeMovieViewModel
import com.ian.app.muviepedia.ui.activity.detail.DetailActivity
import com.ian.app.muviepedia.ui.activity.discover_movie.DiscoverMovieActivity
import com.ian.app.muviepedia.ui.fragment.movie.slidermovieadapter.SliderMovieItemAdapter
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
class HomeFragment : BaseFragment() {
    private val vm: GetHomeMovieViewModel by viewModel()
    private var actualView: View? = null
    private var mHandler: Handler? = null
    private var pageSize: Int? = 0
    private var currentPage = 0

    private var slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            vpNowPlaying?.setCurrentItem(currentPage++, true)
            mHandler?.postDelayed(this, MovieConstant.delayMillis)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mHandler = Handler()
        val views = container?.inflates(R.layout.fragment_home)
        views?.let { initView(it) }
        return views
    }

    override fun initView(view: View) {
        this.actualView = view
        view.tvSeeAllPopularMovie.setOnClickListener {
            context?.startActivity<DiscoverMovieActivity> {
                putExtra(MovieConstant.intentToDiscoverActivity, MovieConstant.popularPagingState)
            }
        }
        view.tvSeeAllTopRatedMovie.setOnClickListener {
            context?.startActivity<DiscoverMovieActivity> {
                putExtra(MovieConstant.intentToDiscoverActivity, MovieConstant.topRatedPagingState)
            }
        }
        view.tvSeeAllUpComingMovie.setOnClickListener {
            context?.startActivity<DiscoverMovieActivity> {
                putExtra(MovieConstant.intentToDiscoverActivity, MovieConstant.upcomingPagingState)
            }
        }
    }

    override fun initLocalData() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.allHomeMovie.observe(this@HomeFragment.viewLifecycleOwner, Observer {
            onSuccessGetPopularMovie(it.first.first.toMutableList())
            onSuccessGetNowPlayingMovie(it.first.second.toMutableList())
            onSuccessGetTopRatedMovie(it.second.first.toMutableList())
            onSuccessGetUpComingMovie(it.second.second.toMutableList())
        })
    }
    override fun initFetchNetworkData() {
      /*  vm.allHomeMovie.observe(this@HomeFragment.viewLifecycleOwner, Observer {
            onSuccessGetPopularMovie(it.first.first.toMutableList())
            onSuccessGetNowPlayingMovie(it.first.second.toMutableList())
            onSuccessGetTopRatedMovie(it.second.first.toMutableList())
            onSuccessGetUpComingMovie(it.second.second.toMutableList())
        })*/
//        vm.liveDataState.observe(this@HomeFragment.viewLifecycleOwner, Observer { extractData(it) })
    }

    override fun onFailedGetData(msg: String?) {
    }

    private fun onSuccessGetNowPlayingMovie(newData: MutableList<MovieData>) {
        pageSize = newData.size
//        initRandomData(newData[Random.nextInt(pageSize!!)])
        actualView?.vpNowPlaying?.adapter = SliderMovieItemAdapter(newData, context)
        actualView?.indicator?.setViewPager(vpNowPlaying)
        if (mHandler != null) {
            mHandler?.removeCallbacks(slideRunnable)
        }
        mHandler?.postDelayed(slideRunnable, MovieConstant.delayMillis)
    }

    private fun onSuccessGetPopularMovie(newData: MutableList<MovieData>) {
        actualView?.shimmerHome?.stopShimmer()
        actualView?.shimmerHome?.gone()
        actualView?.rvPopularMovie?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(MovieConstant.intentToDetail, id)
            }
        })
    }

    private fun onSuccessGetTopRatedMovie(newData: MutableList<MovieData>) {
        actualView?.rvTopRatedMovie?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(MovieConstant.intentToDetail, id)
            }
        })
    }

    private fun onSuccessGetUpComingMovie(newData: MutableList<MovieData>) {
        actualView?.rvUpComingMovie?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.title
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(MovieConstant.intentToDetail, id)
            }
        })
    }

    private fun initRandomData(data: MovieData) {
        actualView?.ivRandomDrink?.loadWithGlide(MovieConstant.imageFormatter + data.poster_path)
        actualView?.ivRandomDrink?.setOnClickListener {
            context?.startActivity<DetailActivity> {
                putExtra(MovieConstant.intentToDetail, data.id)
            }
        }
    }


    private fun extractData(data: BaseState) {
        when (data) {
            is OnFailedGetData -> onFailedGetData(data.msg)
            is OnGetHomeMoviesData -> {
                onSuccessGetPopularMovie(data.data.first.first.toMutableList())
                onSuccessGetNowPlayingMovie(data.data.first.second.toMutableList())
                onSuccessGetTopRatedMovie(data.data.second.first.toMutableList())
                onSuccessGetUpComingMovie(data.data.second.second.toMutableList())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mHandler?.postDelayed(slideRunnable, MovieConstant.delayMillis)
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