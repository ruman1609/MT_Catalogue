package com.rudyrachman16.mtcatalogue.views.home.tabs

import com.rudyrachman16.mtcatalogue.data.Repositories
import junit.framework.TestCase
import org.junit.Before
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TabViewModelTest : TestCase() {

    private lateinit var viewModel: TabViewModel
    private lateinit var mViewModel: TabViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = TabViewModel()
        mViewModel = mock(TabViewModel::class.java)
    }

    fun testGetMovies() {
        `when`(mViewModel.getMovies()).thenReturn(Repositories().getMovies())
        val movieList = viewModel.getMovies()
        val mMovieList = mViewModel.getMovies()
        assertNotNull(movieList)
        assertNotNull(mMovieList)
        assertEquals(movieList, mMovieList)
    }

    fun testGetTvShow() {
        `when`(mViewModel.getTvShow()).thenReturn(Repositories().getTvShow())
        val tvShowList = viewModel.getTvShow()
        val mTvShowList = mViewModel.getTvShow()
        assertNotNull(tvShowList)
        assertNotNull(mTvShowList)
        assertEquals(tvShowList, mTvShowList)
    }

    fun testEmptyArrayCase() {
        val mMovieList = mViewModel.getMovies()
        val mTvShowList = mViewModel.getTvShow()
        assertEquals(0, mMovieList.size)
        assertEquals(0, mTvShowList.size)
    }
}