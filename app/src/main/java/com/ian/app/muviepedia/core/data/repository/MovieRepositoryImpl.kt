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
import com.ian.app.muviepedia.core.data.repository.model.mapLocalMovieListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapRemoteMovieListToDomain
import com.ian.app.muviepedia.core.data.repository.model.mapToDomain
import com.ian.app.muviepedia.core.domain.MovieRepository
import com.ian.app.muviepedia.core.domain.helper.NetworkBoundResource
import com.ian.app.muviepedia.core.domain.model.DomainSource
import com.ian.app.muviepedia.di.qualifier.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
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

    private infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) =
        this.size == other.size && this.toSet() == other.toSet()

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
                        remoteData.data.results.mapRemoteMovieListToDomain()
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
                    .map { it.mapLocalMovieListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data =
                    localDataSource.loadAllMovieDataByType(MovieType.Popular.name).firstOrNull()
                val isExpired = data?.firstOrNull()?.timeStamp?.isExpired()
                return isExpired ?: false
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getPopularMovie()
            }

            override suspend fun isItemSame(): Boolean {
                val localData = localDataSource.loadAllMovieDataByType(MovieType.Popular.name).firstOrNull()
                val remoteData = remoteDataSource.getPopularMovie()
                return if (localData!=null){
                    when(remoteData){
                        is DataSource.Error -> false
                        is DataSource.Success ->{
                            val remoteNames = remoteData.data.results.map { it.title }
                            val localNames = localData.map { it.title }
                            localNames equalsIgnoreOrder remoteNames
                        }
                    }
                } else {
                    false
                }

            }

            override suspend fun clearFirst() {
                localDataSource.deleteAll()
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
                    .map { it.mapLocalMovieListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getNowPlaying()
            }

            override suspend fun isItemSame(): Boolean {
                val localData = localDataSource.loadAllMovieDataByType(MovieType.NowPlaying.name).firstOrNull()
                val remoteData = remoteDataSource.getNowPlaying()
                return if (localData!=null){
                    when(remoteData){
                        is DataSource.Error -> false
                        is DataSource.Success ->{
                            val remoteNames = remoteData.data.results.map { it.title }
                            val localNames = localData.map { it.title }
                            localNames equalsIgnoreOrder remoteNames
                        }
                    }
                } else {
                    false
                }
            }

            override suspend fun clearFirst() {
                localDataSource.deleteAll()
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
                    .map { it.mapLocalMovieListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.TopRated.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getTopRatedMovie()
            }

            override suspend fun clearFirst() {
                localDataSource.deleteAll()
            }

            override suspend fun isItemSame(): Boolean {
                val localData = localDataSource.loadAllMovieDataByType(MovieType.TopRated.name).firstOrNull()
                val remoteData = remoteDataSource.getTopRatedMovie()
                return if (localData!=null){
                    when(remoteData){
                        is DataSource.Error -> false
                        is DataSource.Success ->{
                            val remoteNames = remoteData.data.results.map { it.title }
                            val localNames = localData.map { it.title }
                            localNames equalsIgnoreOrder remoteNames
                        }
                    }
                } else {
                    false
                }
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
                    .map { it.mapLocalMovieListToDomain() }
            }

            override suspend fun isExpired(): Boolean {
                val data = localDataSource.loadAllMovieDataByType(MovieType.UpComing.name).first()
                return data.first().timeStamp.isExpired()
            }

            override suspend fun createCall(): DataSource<BaseResponse<MovieDataResponse>> {
                return remoteDataSource.getUpComingMovie()
            }

            override suspend fun clearFirst() {
                localDataSource.deleteAll()
            }

            override suspend fun isItemSame(): Boolean {
                val localData = localDataSource.loadAllMovieDataByType(MovieType.UpComing.name).firstOrNull()
                val remoteData = remoteDataSource.getUpComingMovie()
                return if (localData!=null){
                    when(remoteData){
                        is DataSource.Error -> false
                        is DataSource.Success ->{
                            val remoteNames = remoteData.data.results.map { it.title }
                            val localNames = localData.map { it.title }
                            localNames equalsIgnoreOrder remoteNames
                        }
                    }
                } else {
                    false
                }
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