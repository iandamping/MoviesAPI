package com.ian.app.muviepedia.core.domain

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.domain.model.DomainSource
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun fetchDetailTv(tvID: Int): Flow<DomainSource<TelevisionDetail>>

    fun fetchSimilarTv(tvID: Int): Flow<DomainSource<List<Television>>>

    fun prefetchPopularTv(): Flow<DomainSource<List<Television>>>

    fun prefetchTopRatedTv(): Flow<DomainSource<List<Television>>>

    fun prefetchAiringTodayTv(): Flow<DomainSource<List<Television>>>

    fun prefetchOnAirTv(): Flow<DomainSource<List<Television>>>
}
