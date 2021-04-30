package com.rudyrachman16.mtcatalogue.views.detail

import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.TvShow
import com.rudyrachman16.mtcatalogue.utils.DummyData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetailViewModelTest : TestCase() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var mMovie: Movie
    private lateinit var mTvShow: TvShow

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetMovie() {
        viewModel.setMovie(DummyData.dummyMovie)
        val movie = viewModel.movie
        assertNotNull(movie)
        assertEquals(DummyData.dummyMovie, movie)
    }

    @Test
    fun testGetTvShow() {
        viewModel.setTvShow(DummyData.dummyTvShow)
        val tvShow = viewModel.tvShow
        assertNotNull(tvShow)
        assertEquals(DummyData.dummyTvShow, tvShow)
    }

    @Test
    fun testNullCase() {
        val movie = viewModel.movie
        val tvShow = viewModel.tvShow
        assertNull(movie)
        assertNull(tvShow)
    }
}