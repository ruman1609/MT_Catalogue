package com.rudyrachman16.mtcatalogue.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    @SerializedName("overview") val desc: String,
    @SerializedName("release_date") val date: String,
    @SerializedName("poster_path") val imgUrl: String,
    @SerializedName("backdrop_path") val bgUrl: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("vote_count") val voter: Int
) : Parcelable