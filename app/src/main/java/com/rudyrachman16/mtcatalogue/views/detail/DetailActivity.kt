package com.rudyrachman16.mtcatalogue.views.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rudyrachman16.mtcatalogue.BuildConfig
import com.rudyrachman16.mtcatalogue.R
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.TvShow
import com.rudyrachman16.mtcatalogue.databinding.ActivityDetailBinding
import com.rudyrachman16.mtcatalogue.views.home.HomeAdapter

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_MOV = "com.rudyrachman16.mtcatalogue.views.detail.key_mov"
        const val KEY_TV = "com.rudyrachman16.mtcatalogue.views.detail.key_tv"
        const val KEY_POS = "com.rudyrachman16.mtcatalogue.views.detail.key_pos"
        const val KEY_NUM = "com.rudyrachman16.mtcatalogue.views.detail.key_num"
    }

    private var binding: ActivityDetailBinding? = null
    private val bind get() = binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)
        val color =
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) resources.getColor(R.color.white)
            else resources.getColor(R.color.white, null)
        bind.detCollapse.apply {
            setExpandedTitleColor(color)
            setExpandedTitleTextAppearance(R.style.Text_H1)
            setStatusBarScrimColor(color)
            setCollapsedTitleTextColor(color)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.extras?.getParcelable<Movie>(KEY_MOV)
        val tvShow = intent.extras?.getParcelable<TvShow>(KEY_TV)
        val viewModel: DetailViewModel by viewModels()
        if (movie != null || tvShow != null) {
            if (movie != null) {
                viewModel.setMovie(movie)
                initMovie(viewModel.movie!!)
            } else if (tvShow != null) {
                viewModel.setTvShow(tvShow)
                initTvShow(viewModel.tvShow!!)
            }
        }
    }

    private fun initTvShow(tvShow: TvShow) {
        initAll(tvShow.title, tvShow.imgUrl, tvShow.bgUrl, tvShow.desc, tvShow.rating, tvShow.voter)
        bind.detType.text = getString(R.string.tv_show)
        bind.detAirTitle.text = getString(R.string.first_air)
        bind.detDate.text = HomeAdapter.parseDate(tvShow.date)
    }

    private fun initMovie(movie: Movie) {
        initAll(movie.title, movie.imgUrl, movie.bgUrl, movie.desc, movie.rating, movie.voter)
        bind.detType.text = getString(R.string.movie)
        bind.detAirTitle.text = getString(R.string.release_date)
        bind.detDate.text = HomeAdapter.parseDate(movie.date)
    }

    private fun initAll(
        title: String, imgURL: String, bgURL: String, desc: String, rating: Double, voter: Int
    ) {
        this.title = title
        bind.detRating.text = rating.toString()
        bind.detUserRate.text = getString(R.string.user_rate, voter)
        Glide
            .with(applicationContext).load(BuildConfig.BASE_IMAGE + imgURL)
            .apply(RequestOptions.placeholderOf(R.drawable.loading))
            .error(R.drawable.error).into(bind.detImg)
        Glide
            .with(applicationContext).load(BuildConfig.BASE_IMAGE + bgURL)
            .apply(RequestOptions.placeholderOf(R.drawable.loading))
            .error(R.drawable.error).into(bind.detBg)
        bind.detShare.setOnClickListener {
            HomeAdapter.share(
                this, intent.getIntExtra(KEY_POS, -1),
                intent.getIntExtra(KEY_NUM, 0), title, rating, voter
            )
        }
        bind.detOverview.text = getString(R.string.overview_in_english_only, title)
        bind.detDesc.text = desc
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}