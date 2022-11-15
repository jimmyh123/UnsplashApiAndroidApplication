package com.jimmyh123.retrofitapplication.data.remote.dto.subclasses

import com.google.gson.annotations.SerializedName

data class Sponsorship(
    @SerializedName("impression_urls")
    val impressionUrls: List<Any>,
    val sponsor: Sponsor,
    val tagline: String,
    @SerializedName("tagline_url")
    val taglineUrl: String
)