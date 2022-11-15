package com.jimmyh123.retrofitapplication.domain.model

import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Links
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Tag
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Urls
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.User

data class UnsplashDetail(
    val id: String,
    val altDescription: String?,
    val createdAt: String?,
    val description: String?,
    val downloads: Int?,
    val likes: Int,
    val links: Links?,
    val likedByUser: Boolean,
    val tags: List<Tag>,
    val updatedAt: String?,
    val urls: Urls?,
    val user: User?,
    val views: Int?
)
