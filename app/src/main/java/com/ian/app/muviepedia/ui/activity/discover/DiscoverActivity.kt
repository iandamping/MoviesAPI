package com.ian.app.muviepedia.ui.activity.discover

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseActivity
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.paging.GetPagingDataViewModel
import com.ian.app.muviepedia.ui.activity.detail.DetailActivity
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.MovieConstant.diffCallbacks
import com.ian.app.muviepedia.util.MovieConstant.intentToDetail
import com.ian.app.muviepedia.util.MovieConstant.intentToDiscoverActivity
import com.ian.recyclerviewhelper.helper.setUpPagingWithGrid
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.item_discover_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverActivity : BaseActivity() {
    private val vm: GetPagingDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
    }

    private fun onSuccessGetData(data: PagedList<MovieData>?) {
        shimmerGridListContainer?.stopShimmer()
        shimmerGridListContainer?.gone()
        rvDiscoverMovie.setUpPagingWithGrid(data, R.layout.item_discover_movie, 2, {
            ivDiscoverMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
        }, diffCallbacks, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onFailedGetData(msg: String?) {
    }

    override fun initLocalData() {
    }

    override fun initFetchNetworkData() {
        val states = intent.getStringExtra(intentToDiscoverActivity)
        if (states != null) {
            vm.getAllMovies(states).observe(this, Observer {
                onSuccessGetData(it)
            })
        }
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