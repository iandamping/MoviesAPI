package com.ian.app.muviepedia.ui.fragment.tv

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ian.app.helper.util.*
import com.ian.app.muviepedia.BuildConfig
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseFragment
import com.ian.app.muviepedia.base.BaseState
import com.ian.app.muviepedia.base.OnFailedGetData
import com.ian.app.muviepedia.base.OnGetHomeTvData
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.viewmodel.tv.GetHomeTvViewModel
import com.ian.app.muviepedia.ui.activity.detail_tv.DetailTvActivity
import com.ian.app.muviepedia.ui.activity.discover_tv.DiscoverTvActivity
import com.ian.app.muviepedia.ui.fragment.tv.slidertvadapter.SliderTvItemAdapter
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.fragment_tv.*
import kotlinx.android.synthetic.main.fragment_tv.view.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class TvFragment : BaseFragment() {
    private val vm: GetHomeTvViewModel by viewModel()
    private var actualView: View? = null
    private var mHandler: Handler? = null
    private var pageSize: Int? = 0
    private var currentPage = 0

    private var slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            vpPopularTv?.setCurrentItem(currentPage++, true)
            mHandler?.postDelayed(this, MovieConstant.delayMillis)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mHandler = Handler()
        val views = container?.inflates(R.layout.fragment_tv)
        views?.let { initView(it) }
        return views
    }

    override fun initView(view: View) {
        this.actualView = view
        view.tvSeeAllTopRatedTv.setOnClickListener {
            context?.startActivity<DiscoverTvActivity> {
                putExtra(MovieConstant.intentToDiscoverTvActivity, MovieConstant.topRatedTvPagingState)
            }
        }
        view.tvSeeAllAiringTodayTv.setOnClickListener {
            context?.startActivity<DiscoverTvActivity> {
                putExtra(MovieConstant.intentToDiscoverTvActivity, MovieConstant.airingTodayPagingState)
            }
        }
        view.tvSeeAllOnAirTv.setOnClickListener {
            context?.startActivity<DiscoverTvActivity> {
                putExtra(MovieConstant.intentToDiscoverTvActivity, MovieConstant.onAirPagingState)
            }
        }
    }

    override fun initFetchNetworkData() {
        vm.getTv().apply {
            vm.liveDataState.observe(this@TvFragment.viewLifecycleOwner, Observer { extractData(it) })
        }
    }

    private fun extractData(data: BaseState) {
        when (data) {
            is OnFailedGetData -> onFailedGetData(data.msg)
            is OnGetHomeTvData -> {
                onSuccessGetNowPopularTv(data.data.first.first.toMutableList())
                onSuccessGetTopRatedTv(data.data.first.second.toMutableList())
                onSuccessGetAiringTodayTv(data.data.second.first.toMutableList())
                onSuccessGetOnAirTv(data.data.second.second.toMutableList())
            }
        }
    }

    private fun onSuccessGetNowPopularTv(newData: MutableList<TvData>) {
        pageSize = newData.size
        actualView?.vpPopularTv?.adapter = SliderTvItemAdapter(newData, context)
        actualView?.indicatorTv?.setViewPager(vpPopularTv)
        if (mHandler != null) {
            mHandler?.removeCallbacks(slideRunnable)
        }
        mHandler?.postDelayed(slideRunnable, MovieConstant.delayMillis)
    }

    private fun onSuccessGetTopRatedTv(newData: MutableList<TvData>) {
        actualView?.shimmerHomeTv?.stopShimmer()
        actualView?.shimmerHomeTv?.gone()
        actualView?.rvTopRatedTv?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.name
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailTvActivity> {
                putExtra(MovieConstant.intentToTvDetail, id)
            }
        })
    }

    private fun onSuccessGetAiringTodayTv(newData: MutableList<TvData>) {
        actualView?.rvTAiringTodayTv?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.name
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailTvActivity> {
                putExtra(MovieConstant.intentToTvDetail, id)
            }
        })
    }

    private fun onSuccessGetOnAirTv(newData: MutableList<TvData>) {
        actualView?.rvOnAirTv?.setUpHorizontal(newData, R.layout.item_home, {
            with(this) {
                tvHomeMovieName.text = it.name
                ivHomeMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            }
        }, {
            context?.startActivity<DetailTvActivity> {
                putExtra(MovieConstant.intentToTvDetail, id)
            }
        })
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
        actualView?.shimmerHomeTv?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        actualView?.shimmerHomeTv?.startShimmer()
    }

    override fun initLocalData() {
    }

    override fun onFailedGetData(msg: String?) {
        if (BuildConfig.DEBUG) logE(msg)
    }

}