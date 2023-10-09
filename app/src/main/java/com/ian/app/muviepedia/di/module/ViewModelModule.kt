package com.ian.app.muviepedia.di.module

import androidx.lifecycle.ViewModel
import com.ian.app.muviepedia.di.module.viewModel.ViewModelKey
import com.ian.app.muviepedia.feature.detail.DetailViewModel
import com.ian.app.muviepedia.feature.home.HomeViewModel
import com.ian.app.muviepedia.feature.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindMovieViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}
