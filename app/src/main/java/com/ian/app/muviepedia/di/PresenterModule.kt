package com.ian.app.muviepedia.di

import com.ian.app.muviepedia.ui.activity.detail.DetailActivity
import com.ian.app.muviepedia.ui.activity.detail.DetailPresenter
import com.ian.app.muviepedia.ui.activity.detail_tv.DetailTvActivity
import com.ian.app.muviepedia.ui.activity.detail_tv.DetailTvPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 *
Created by Ian Damping on 20/06/2019.
Github = https://github.com/iandamping
 */
val detailMoviePresenter = module {
    scope(named<DetailActivity>()) {
        scoped { DetailPresenter(get(), get()) }
    }
}


val detailTvPresenter = module {
    scope(named<DetailTvActivity>()) {
        scoped { DetailTvPresenter(get()) }
    }

}
