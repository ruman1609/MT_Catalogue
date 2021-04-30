package com.rudyrachman16.mtcatalogue.views.home.tabs

import androidx.lifecycle.ViewModel
import com.rudyrachman16.mtcatalogue.data.Repositories
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.TvShow

class TabViewModel : ViewModel() {
    fun getMovies(): ArrayList<Movie> = Repositories().getMovies()
    fun getTvShow(): ArrayList<TvShow> = Repositories().getTvShow()
}