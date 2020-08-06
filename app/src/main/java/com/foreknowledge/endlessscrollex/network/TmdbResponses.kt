package com.foreknowledge.endlessscrollex.network

import com.foreknowledge.endlessscrollex.network.TmdbApi.Companion.BASE_POSTER_URL
import com.google.gson.annotations.SerializedName

/**
 * Created by Yeji on 30,May,2020.
 */
data class TvResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val results: List<TvShow>
)

data class TvShow(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("poster_path")
    private val posterPath: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("origin_country")
    val originCountries: List<String>
) {
    fun getPosterUrl(): String = BASE_POSTER_URL + posterPath
}