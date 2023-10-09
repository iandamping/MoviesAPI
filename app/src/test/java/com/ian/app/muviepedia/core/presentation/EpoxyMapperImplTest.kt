package com.ian.app.muviepedia.core.presentation

import com.ian.app.muviepedia.core.data.repository.model.Movie
import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.presentation.model.EpoxyMovie
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyNowPlayingData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyPopularData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyPopularTvData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxySearchMovieData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyTopRatedData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyTopRatedTvData
import com.ian.app.muviepedia.core.presentation.model.toListEpoxyUpComingData
import com.ian.app.muviepedia.feature.home.epoxy.movie.nowPlaying.EpoxyNowPlayingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.popular.EpoxyPopularMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.topRated.EpoxyTopRatedMovieData
import com.ian.app.muviepedia.feature.home.epoxy.movie.upComing.EpoxyUpComingMovieData
import com.ian.app.muviepedia.feature.home.epoxy.television.popular.EpoxyPopularTelevisionData
import com.ian.app.muviepedia.feature.home.epoxy.television.topRated.EpoxyTopRatedTelevisionData
import com.ian.app.muviepedia.feature.search.epoxy.EpoxySearchMovieData
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EpoxyMapperImplTest {

    private lateinit var sut: EpoxyMapper

    @Before
    fun setUp() {
        sut = EpoxyMapperImpl()
    }

    @Test
    fun extractMovieToEpoxy() {
        //arrange
        val movies: List<Movie> =
            listOf(Movie(1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val result = sut.extractMovieToEpoxy(movies)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun extractTelevisionToEpoxy() {
        val tvs: List<Television> =
            listOf(Television("a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val expected: Set<EpoxyTelevision> =
            setOf(EpoxyTelevision(1, "a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))

        val result = sut.extractTelevisionToEpoxy(tvs)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyPopularMovieListMapper() {
        val data: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: List<EpoxyPopularMovieData.MovieData> = listOf(
            EpoxyPopularMovieData.MovieData(
                1,
                1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyMovie>::toListEpoxyPopularData)

        every {
            data.toListEpoxyPopularData()
        } returns expected

        val result = sut.epoxyPopularMovieListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyNowPlayingMovieListMapper() {
        val data: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: List<EpoxyNowPlayingMovieData.MovieData> = listOf(
            EpoxyNowPlayingMovieData.MovieData(
                1,
                1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyMovie>::toListEpoxyNowPlayingData)

        every {
            data.toListEpoxyNowPlayingData()
        } returns expected

        val result = sut.epoxyNowPlayingMovieListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyTopRatedMovieListMapper() {
        val data: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: List<EpoxyTopRatedMovieData.MovieData> = listOf(
            EpoxyTopRatedMovieData.MovieData(
                1,
                1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyMovie>::toListEpoxyTopRatedData)

        every {
            data.toListEpoxyTopRatedData()
        } returns expected

        val result = sut.epoxyTopRatedMovieListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyUpComingMovieListMapper() {
        val data: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: List<EpoxyUpComingMovieData.MovieData> = listOf(
            EpoxyUpComingMovieData.MovieData(
                1,
                1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyMovie>::toListEpoxyUpComingData)

        every {
            data.toListEpoxyUpComingData()
        } returns expected

        val result = sut.epoxyUpComingMovieListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxySearchMovieListMapper() {
        val data: Set<EpoxyMovie> =
            setOf(EpoxyMovie(1, 1, 1, false, 1.1, "a", 1.1, "a", "a", "a", "a", false, "a", "a"))
        val expected: List<EpoxySearchMovieData.MovieData> = listOf(
            EpoxySearchMovieData.MovieData(
                1,
                1,
                1,
                false,
                1.1,
                "a",
                1.1,
                "a",
                "a",
                "a",
                "a",
                false,
                "a",
                "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyMovie>::toListEpoxySearchMovieData)

        every {
            data.toListEpoxySearchMovieData()
        } returns expected

        val result = sut.epoxySearchMovieListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyPopularTelevisionListMapper() {
        val data: Set<EpoxyTelevision> =
            setOf(EpoxyTelevision(1, "a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val expected: List<EpoxyPopularTelevisionData.TelevisionData> = listOf(
            EpoxyPopularTelevisionData.TelevisionData(
                1, "a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyTelevision>::toListEpoxyPopularTvData)

        every {
            data.toListEpoxyPopularTvData()
        } returns expected

        val result = sut.epoxyPopularTelevisionListMapper(data)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun epoxyTopRatedTelevisionListMapper() {
        val data: Set<EpoxyTelevision> =
            setOf(EpoxyTelevision(1, "a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"))
        val expected: List<EpoxyTopRatedTelevisionData.TelevisionData> = listOf(
            EpoxyTopRatedTelevisionData.TelevisionData(
                1, "a", "a", 1.1, 1, "a", "a", "a", 1, 1.1, "a", "a"
            )
        )
        mockkStatic("com.ian.app.muviepedia.core.presentation.model.EpoxyDataMapperKt")
        mockkStatic(Set<EpoxyTelevision>::toListEpoxyTopRatedTvData)

        every {
            data.toListEpoxyTopRatedTvData()
        } returns expected

        val result = sut.epoxyTopRatedTelevisionListMapper(data)

        Assert.assertEquals(expected, result)
    }
}