package com.ian.app.muviepedia.feature.search.controller

import com.ian.app.muviepedia.core.data.repository.model.Movie

data class EpoxySearchData(
    val loading: List<Int>,
    val searchMovie: List<Movie>,
    val error: List<String>
) {
    companion object {
        fun init(): EpoxySearchData {
            return EpoxySearchData(
                searchMovie = emptyList(),
                loading = emptyList(),
                error = emptyList(),
            )
        }
    }
}


//data class EpoxySearchData(
//    val searchMovie: List<EpoxySearchMovieData>
//) {
//    companion object {
//        fun init(): EpoxySearchData {
//            return EpoxySearchData(
//                searchMovie = emptyList(),
//            )
//        }
//    }
//}
