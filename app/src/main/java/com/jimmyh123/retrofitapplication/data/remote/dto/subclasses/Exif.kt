package com.jimmyh123.retrofitapplication.data.remote.dto.subclasses

data class Exif(
    val aperture: String,
    val exposure_time: String,
    val focal_length: String,
    val iso: Int,
    val make: String,
    val model: String,
    val name: String
)