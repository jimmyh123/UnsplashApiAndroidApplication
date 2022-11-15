package com.jimmyh123.retrofitapplication.data.remote.dto.subclasses

data class PreviewPhoto(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
//    val urls: UrlsXX
    val urls: Urls
)