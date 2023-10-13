package com.ian.app.muviepedia.di.factory.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ian.app.muviepedia.feature.detail.DetailFragment
import com.ian.app.muviepedia.feature.home.HomeFragment
import com.ian.app.muviepedia.feature.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentFactoryModule {

    @Binds
    abstract fun bindFragmentFactory(factory: DefaultFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindsDetailFragment(fragment: DetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindsHomeFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    abstract fun bindsSearchFragment(fragment: SearchFragment): Fragment
}