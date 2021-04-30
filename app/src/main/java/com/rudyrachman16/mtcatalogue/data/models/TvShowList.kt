package com.rudyrachman16.mtcatalogue.data.models

import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("results") val list: ArrayList<TvShow>
)
