package com.ian.app.muviepedia.ui.fragment.saved_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ian.app.helper.util.gone
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.base.BaseFragment
import com.ian.app.muviepedia.base.OnGetLocalData
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.viewmodel.GetLocalDataViewModel
import com.ian.app.muviepedia.ui.activity.detail.DetailActivity
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.recyclerviewhelper.helper.setUpVertical
import kotlinx.android.synthetic.main.fragment_saved_movie.view.*
import kotlinx.android.synthetic.main.item_saved_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 06/06/2019.
Github = https://github.com/iandamping
 */
class SavedMovieFragment : BaseFragment() {

    private val vm: GetLocalDataViewModel by viewModel()
    private var actualView: View? = null

    override fun initLocalData() {
        vm.getLocalData().apply {
            vm.liveDataState.observe(this@SavedMovieFragment.viewLifecycleOwner, Observer {
                when (it) {
                    is OnGetLocalData -> it.data.observe(this@SavedMovieFragment.viewLifecycleOwner, Observer { localData ->
                        onSuccessGetLocalData(localData)
                    })
                }
            })
        }
    }

    override fun initFetchNetworkData() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = container?.inflates(R.layout.fragment_saved_movie)
        views?.let { initView(it) }
        return views
    }

    private fun onSuccessGetLocalData(data: List<LocalMovieData>) {
        actualView?.shimmerSavedMovie?.stopShimmer()
        actualView?.shimmerSavedMovie?.gone()
        actualView?.rvSavedMovie?.setUpVertical(data, R.layout.item_saved_movie, {
            with(this) {
                ivSavedMovieMovie.loadWithGlide(MovieConstant.imageFormatter + it.poster_path)
                tvSavedMovieMovieTittle.text = it.title
            }
        }, {
            context?.startActivity<DetailActivity> {
                putExtra(MovieConstant.intentToDetail, id)
            }
        })
    }

    override fun initView(view: View) {
        this.actualView = view
    }

    override fun onFailedGetData(msg: String?) {
        actualView?.shimmerSavedMovie?.stopShimmer()
        actualView?.shimmerSavedMovie?.gone()
    }

    override fun onPause() {
        super.onPause()
        actualView?.shimmerSavedMovie?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        actualView?.shimmerSavedMovie?.startShimmer()
    }
}