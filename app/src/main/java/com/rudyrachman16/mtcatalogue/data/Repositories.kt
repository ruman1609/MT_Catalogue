package com.rudyrachman16.mtcatalogue.data

import com.google.gson.Gson
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.MovieList
import com.rudyrachman16.mtcatalogue.data.models.TvShow
import com.rudyrachman16.mtcatalogue.data.models.TvShowList
import com.rudyrachman16.mtcatalogue.utils.DummyData

class Repositories {
    fun getMovies(): ArrayList<Movie> =
        Gson().fromJson(DummyData.jsonMovie, MovieList::class.java).list

    fun getTvShow(): ArrayList<TvShow> =
        Gson().fromJson(DummyData.jsonTvShow, TvShowList::class.java).list
}