package com.jimmyh123.retrofitapplication.domain.model

import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Urls
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.User

data class Unsplash(
    val id: String,
    val likes: Int,
    val urls: Urls?,
    val user: User?,
    val likedByUser: Boolean?
)
