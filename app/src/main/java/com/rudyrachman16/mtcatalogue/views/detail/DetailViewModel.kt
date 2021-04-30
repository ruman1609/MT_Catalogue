package com.rudyrachman16.mtcatalogue.views.detail

import androidx.lifecycle.ViewModel
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.TvShow

class DetailViewModel : ViewModel() {
    var movie: Movie? = null
        private set
    var tvShow: TvShow? = null
        private set

    fun setMovie(movie: Movie) {
        this.movie = movie
    }

    fun setTvShow(tvShow: TvShow) {
        this.tvShow = tvShow
    }
}