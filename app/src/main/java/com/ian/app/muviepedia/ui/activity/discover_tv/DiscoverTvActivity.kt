package com.ian.app.muviepedia.ui.activity.discover_tv

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseActivity
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.data.paging.tv.GetTvPagingDataViewModel
import com.ian.app.muviepedia.ui.activity.detail_tv.DetailTvActivity
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.activity_tv_discover.*
import kotlinx.android.synthetic.main.item_discover_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class DiscoverTvActivity : BaseActivity() {

    private val vm: GetTvPagingDataViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_discover)
    }

    private fun onSuccessGetData(data: PagedList<TvData>?) {
        shimmerGridListTvContainer?.stopShimmer()
        shimmerGridListTvContainer?.gone()
        rvDiscoverTv.setUpPagingWithGrid(data, R.layout.item_discover_movie, 2, {
            ivDiscoverMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
        }, MovieConstant.movieDiffCallbacks, {
            startActivity<DetailTvActivity> {
                putExtra(MovieConstant.intentToTvDetail, id)
            }
        })
    }

    override fun initView() {
    }

    override fun initFetchNetworkData() {
        val states = intent.getStringExtra(MovieConstant.intentToDiscoverTvActivity)
        if (states != null) {
            vm.getAllTvs(states).observe(this, Observer {
                onSuccessGetData(it)
            })
        }
    }

    override fun initLocalData() {
    }

    override fun onFailedGetData(msg: String?) {
    }

    override fun onPause() {
        super.onPause()
        shimmerGridListTvContainer?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmerGridListTvContainer?.startShimmer()
    }
}