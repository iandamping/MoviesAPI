package com.ian.app.muviepedia.core.domain

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.domain.model.DomainSource
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun fetchDetailTv(tvID: Int): Flow<DomainSource<TelevisionDetail>>

    fun fetchSimilarTv(tvID: Int): Flow<DomainSource<List<Television>>>

    fun fetchPopularTv(): Flow<DomainSource<List<Television>>>

    fun fetchTopRatedTv(): Flow<DomainSource<List<Television>>>

    fun fetchAiringTodayTv(): Flow<DomainSource<List<Television>>>

    fun fetchOnAirTv(): Flow<DomainSource<List<Television>>>

    fun fetchSearchTv(query: String): Flow<DomainSource<List<Television>>>
}
