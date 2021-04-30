package com.rudyrachman16.mtcatalogue.views.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rudyrachman16.mtcatalogue.BuildConfig
import com.rudyrachman16.mtcatalogue.R
import com.rudyrachman16.mtcatalogue.data.models.Movie
import com.rudyrachman16.mtcatalogue.data.models.TvShow
import com.rudyrachman16.mtcatalogue.databinding.PerViewBinding
import com.rudyrachman16.mtcatalogue.views.home.tabs.TabAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter<T>(
    private val shareCallback: (
        number: Int, title: String, rating: Double, voter: Int
    ) -> Unit, private val clickCallback: (model: T, number: Int) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter<T>.HomeHolder>() {
    companion object {
        fun share(
            activity: Activity, position: Int, number: Int,
            title: String, rating: Double, voter: Int
        ) {
            ShareCompat.IntentBuilder.from(activity).apply {
                setType("text/plain")
                setChooserTitle("Share to?")
                setText(
                    "Popular ${activity.applicationContext.getString(TabAdapter.TAB_TITLE[position])} #$number\n" +
                            "Title: $title\n" +
                            "Have $rating from $voter users"
                )
                startChooser()
            }
        }

        fun parseDate(oldStringDate: String): String {
            val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val oldDate = oldFormat.parse(oldStringDate)!!
            val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return newFormat.format(oldDate)
        }
    }

    inner class HomeHolder(private val bind: PerViewBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(model: T) {
            itemView.setOnClickListener { clickCallback(model, adapterPosition + 1) }
            when (model) {
                is Movie -> {
                    initiate(model.id, model.title, model.imgUrl, model.rating, model.voter)
                    bind.perAirTitle.text = itemView.context.getString(R.string.release_date)
                    bind.perDate.text = parseDate(model.date)
                }
                is TvShow -> {
                    initiate(model.id, model.title, model.imgUrl, model.rating, model.voter)
                    bind.perAirTitle.text = itemView.context.getString(R.string.first_air)
                    bind.perDate.text = parseDate(model.date)
                }
            }
        }

        private fun initiate(
            id: String, title: String, imgURL: String, rating: Double, voter: Int
        ) {
            bind.perID.text = id
            bind.perTitle.text = title
            bind.perRating.text = rating.toString()
            bind.perUserRate.text = itemView.context.getString(R.string.user_rate, voter)
            bind.perShare.setOnClickListener {
                shareCallback(adapterPosition + 1, title, rating, voter)
            }
            Glide
                .with(itemView.context).load(BuildConfig.BASE_IMAGE + imgURL)
                .apply(RequestOptions.placeholderOf(R.drawable.loading))
                .apply(RequestOptions.overrideOf(100, 200))
                .error(R.drawable.error).into(bind.perImage)
        }
    }

    private val list = ArrayList<T>()
    fun setList(model: ArrayList<T>) {
        if (model.isEmpty()) return
        list.clear()
        list.addAll(model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder =
        HomeHolder(PerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size
}