package com.ian.app.muviepedia.core.data.repository

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.mapToDatabase
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.tv.TvType
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.TvDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.tv.TvRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.data.repository.model.mapListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.TvRepository
import com.ian.app.muviepedia.core.domain.helper.NetworkBoundResource
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.di.qualifier.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvRemoteDataSource,
    private val localDataSource: TvLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : TvRepository {

    companion object {
        private val CACHE_EXPIRY = TimeUnit.HOURS.toMillis(1)
    }

    private fun Long.isExpired(): Boolean = (System.currentTimeMillis() - this) > CACHE_EXPIRY

    override fun fetchDetailTv(tvID: Int): Flow<DomainSource<TelevisionDetail>> {
        return flow {
            when (val remoteData = remoteDataSource.getDetailTv(tvID)) {
                is DataSource.Error -> emit(DomainSource.Error(remoteData.message))
                is DataSource.Success -> {
                    val data = withContext(defaultDispatcher) {
                        remoteData.data.mapToDomain()
                    }
                    emit(DomainSource.Success(data))
                }
            }
        }
    }

    override fun fetchSimilarTv(tvID: Int): Flow<DomainSource<List<Television>>> {
        return flow {
            when (val remoteData = remoteDataSource.getSimilarTv(tvID)) {
                is DataSource.Error -> emit(DomainSource.Error(remoteData.message))
                is DataSource.Success -> {
                    val data = withContext(defaultDispatcher) {
                        remoteData.data.results.mapListToDomain()
                    }
                    emit(DomainSource.Success(data))
                }
            }
        }
    }

    override fun prefetchPopularTv(): Flow<DomainSource<List<Television>>> {
        return object :
            NetworkBoundResource<List<Television>, BaseResponse<TvDataResponse>>() {

            override fun loadFromDB(): Flow<List<Television>> {
                return localDataSource.loadAllTvDataByType(TvType.Popular.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllTvDataByType(TvType.Popular.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<TvDataResponse>> {
                return remoteDataSource.getPopularTv()
            }

            override suspend fun saveCallResult(data: BaseResponse<TvDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = TvType.Popular.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertTvData(*inputData)
            }

            override fun shouldFetch(data: List<Television>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun prefetchTopRatedTv(): Flow<DomainSource<List<Television>>> {
        return object :
            NetworkBoundResource<List<Television>, BaseResponse<TvDataResponse>>() {

            override fun loadFromDB(): Flow<List<Television>> {
                return localDataSource.loadAllTvDataByType(TvType.TopRated.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllTvDataByType(TvType.TopRated.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<TvDataResponse>> {
                return remoteDataSource.getPopularTv()
            }

            override suspend fun saveCallResult(data: BaseResponse<TvDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = TvType.TopRated.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertTvData(*inputData)
            }

            override fun shouldFetch(data: List<Television>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun prefetchAiringTodayTv(): Flow<DomainSource<List<Television>>> {
        return object :
            NetworkBoundResource<List<Television>, BaseResponse<TvDataResponse>>() {

            override fun loadFromDB(): Flow<List<Television>> {
                return localDataSource.loadAllTvDataByType(TvType.AiringToday.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllTvDataByType(TvType.AiringToday.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<TvDataResponse>> {
                return remoteDataSource.getPopularTv()
            }

            override suspend fun saveCallResult(data: BaseResponse<TvDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = TvType.AiringToday.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertTvData(*inputData)
            }

            override fun shouldFetch(data: List<Television>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun prefetchOnAirTv(): Flow<DomainSource<List<Television>>> {
        return object :
            NetworkBoundResource<List<Television>, BaseResponse<TvDataResponse>>() {

            override fun loadFromDB(): Flow<List<Television>> {
                return localDataSource.loadAllTvDataByType(TvType.OnAir.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllTvDataByType(TvType.OnAir.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<TvDataResponse>> {
                return remoteDataSource.getPopularTv()
            }

            override suspend fun saveCallResult(data: BaseResponse<TvDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = TvType.OnAir.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertTvData(*inputData)
            }

            override fun shouldFetch(data: List<Television>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }
}