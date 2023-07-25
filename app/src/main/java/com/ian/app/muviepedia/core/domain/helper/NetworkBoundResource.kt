package com.ian.app.muviepedia.core.domain.helper

import com.ian.app.muviepedia.core.data.model.DataSource
import com.ian.app.muviepedia.core.domain.model.DomainSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<DomainSource<ResultType>> = flow {
        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall()) {

                is DataSource.Success -> {
                    saveCallResult(apiResponse.data)

                    emitAll(loadFromDB().map {
                        DomainSource.Success(
                            it
                        )
                    })
                }

                is DataSource.Error -> {
                    onFetchFailed()
                    emit(
                        DomainSource.Error(
                            apiResponse.message
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                DomainSource.Success(
                    it
                )
            })
        }

        if (isExpired()){
            when (val apiResponse = createCall()) {

                is DataSource.Success -> {
                    saveCallResult(apiResponse.data)

                    emitAll(loadFromDB().map {
                        DomainSource.Success(
                            it
                        )
                    })
                }

                is DataSource.Error -> {
                    onFetchFailed()
                    emit(
                        DomainSource.Error(
                            apiResponse.message
                        )
                    )
                }
            }
        }


    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun isExpired(): Boolean

    protected abstract suspend fun createCall(): DataSource<RequestType>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<DomainSource<ResultType>> = result
}