package com.rudyrachman16.mtcatalogue.data

import com.google.gson.Gson
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.MovieList
import com.rudyrachman16.mtcatalogue.data.models.TvShow
import com.rudyrachman16.mtcatalogue.data.models.TvShowList
import com.rudyrachman16.mtcatalogue.utils.DummyData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat

class RepositoriesTest : TestCase() {
    private var movies = ArrayList<Movie>()
    private var tvShows = ArrayList<TvShow>()

    private val dummyMovie = Movie(
        id = "399566",
        title = "Godzilla vs. Kong",
        desc = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
        date = "2021-03-24",
        imgUrl = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
        bgUrl = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
        rating = 8.3,
        voter = 4450
    )

    private val dummyTvShow = TvShow(
        id = "88396",
        title = "The Falcon and the Winter Soldier",
        desc = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
        date = "2021-03-19",
        imgUrl = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
        bgUrl = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
        rating = 7.8,
        voter = 3759
    )

    private lateinit var mockMovies: MovieList
    private lateinit var mockTvShows: TvShowList

    @Before
    override fun setUp() {
        movies.addAll(Gson().fromJson(DummyData.jsonMovie, MovieList::class.java).list)
        tvShows.addAll(Gson().fromJson(DummyData.jsonTvShow, TvShowList::class.java).list)
        mockMovies = mock(MovieList::class.java)
        mockTvShows = mock(TvShowList::class.java)
    }

    @Test
    fun testGetMovies() {
        assertEquals(20, movies.size)
        assertEquals(dummyMovie.title, movies[0].title)
    }

    @Test
    fun testGetTvShows() {
        assertEquals(20, tvShows.size)
        assertEquals(dummyTvShow.title, tvShows[0].title)
    }

    @Test
    fun testMockMovie() {
        `when`(mockMovies.list).thenReturn(
            Gson().fromJson(
                DummyData.jsonMovie,
                MovieList::class.java
            ).list
        )
        assertEquals(20, mockMovies.list.size)
        assertEquals(dummyMovie.title, mockMovies.list[0].title)
    }

    @Test
    fun testMockTvShow() {
        `when`(mockTvShows.list).thenReturn(
            Gson().fromJson(
                DummyData.jsonTvShow,
                TvShowList::class.java
            ).list
        )
        assertEquals(20, mockTvShows.list.size)
        assertEquals(dummyTvShow.title, mockTvShows.list[0].title)
    }

    @Test
    fun testDate() {
        val date = "2021-12-17"
        val oldFormat = SimpleDateFormat("yyyy-MM-dd")
        val oldDate = oldFormat.parse(date)
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val newStringDate = newFormat.format(oldDate)
        println(newStringDate)
    }
}