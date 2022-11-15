package com.jimmyh123.retrofitapplication.presentation.photo_list

import com.jimmyh123.retrofitapplication.domain.model.Unsplash

data class PhotoListState(
    val isLoading: Boolean = false,
    val photos: List<Unsplash> = emptyList(),
    val error: String = "",
)
