package com.ian.app.muviepedia.core.data.repository

import com.ian.app.muviepedia.core.data.dataSource.cache.db.entity.mapToDatabase
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieLocalDataSource
import com.ian.app.muviepedia.core.data.dataSource.cache.source.movie.MovieType
import com.ian.app.muviepedia.core.data.dataSource.remote.model.BaseResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.model.response.MovieDataResponse
import com.ian.app.muviepedia.core.data.dataSource.remote.source.movie.MovieRemoteDataSource
import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.MovieDetail
import com.ian.app.muviepedia.core.data.repository.model.mapListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.MovieRepository
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

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : MovieRepository {

    companion object {
        private val CACHE_EXPIRY = TimeUnit.HOURS.toMillis(1)
    }

    private fun Long.isExpired(): Boolean = (System.currentTimeMillis() - this) > CACHE_EXPIRY

    override fun fetchDetailMovie(movieId: Int): Flow<DomainSource<MovieDetail>> {
        return flow {
            when (val remoteData = remoteDataSource.getDetailMovie(movieId)) {
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

    override fun fetchSimilarMovie(movieId: Int): Flow<DomainSource<List<Movie>>> {
        return flow {
            when (val remoteData = remoteDataSource.getSimilarMovie(movieId)) {
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


    override fun fetchPopularMovie(): Flow<DomainSource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, BaseResponse<MovieDataResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.loadAllMovieDataByType(MovieType.Popular.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.Popular.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getPopularMovie()
            }

            override suspend fun saveCallResult(data: BaseResponse<MovieDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = MovieType.Popular.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertMovie(*inputData)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun fetchNowPlayingMovie(): Flow<DomainSource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, BaseResponse<MovieDataResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getPopularMovie()
            }

            override suspend fun saveCallResult(data: BaseResponse<MovieDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = MovieType.NowPlaying.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertMovie(*inputData)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun fetchTopRatedMovie(): Flow<DomainSource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, BaseResponse<MovieDataResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.loadAllMovieDataByType(MovieType.TopRated.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.TopRated.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getPopularMovie()
            }

            override suspend fun saveCallResult(data: BaseResponse<MovieDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = MovieType.TopRated.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertMovie(*inputData)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun fetchUpComingMovie(): Flow<DomainSource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, BaseResponse<MovieDataResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.loadAllMovieDataByType(MovieType.UpComing.name)
                    .map { it.mapListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.UpComing.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getPopularMovie()
            }

            override suspend fun saveCallResult(data: BaseResponse<MovieDataResponse>) {
                val inputData = withContext(defaultDispatcher) {
                    data.results.map {
                        it.mapToDatabase(
                            type = MovieType.UpComing.name,
                            timeStamp = System.currentTimeMillis()
                        )
                    }.toTypedArray()
                }
                localDataSource.insertMovie(*inputData)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }


}