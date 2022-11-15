package com.jimmyh123.retrofitapplication.presentation.photo_detail

import com.jimmyh123.retrofitapplication.domain.model.UnsplashDetail

data class PhotoDetailState(
    val isLoading: Boolean = false,
    val photo: UnsplashDetail? = null,
    val error: String = "",
)
